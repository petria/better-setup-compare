package com.airiot.fi.service.ai;

import com.airiot.fi.model.ini.scan.SetupIniFileScanStats;
import com.airiot.fi.service.SetupsService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiService {

  private static final String SYSTEM_PROMPT = "You are helpful Assetto Corsa Sim Racing game expert that can analyze game car setup and give characteristic how setup effects car behaviour. When given multiple saved setup ini files you can analyze the differences between setups and  what is their difference in car driving.";

  private final SetupsService setupsService;

  private final ChatClient chatClient;

  private final ServerProperties serverProperties;

  private final ChatMemory chatMemory = MessageWindowChatMemory.builder()
      .maxMessages(1000)
      .build();

  private static final String CHAT_CONVERSATION_ID = "default";


  public AiService(SetupsService setupsService, ChatClient chatClient, ServerProperties serverProperties) {
    this.setupsService = setupsService;
    this.chatClient = chatClient;
    this.serverProperties = serverProperties;
  }

  public Flux<String> aiChatStream(String prompt) {
    return chatClient.prompt()
        .tools(new SetupQueryTool(setupsService))
        .user(prompt)
        .system(SYSTEM_PROMPT)
        .stream()
        .content();
  }

  /**
   * Sends the given prompt to the Ollama server specified by hostUrl
   * and returns the model's text output.
   *
   * @param hostUrl    Base URL of the Ollama host, e.g. "http://localhost:11434"
   * @param promptText Prompt text to send
   * @return Model output text
   */
  public String aiChat(String hostUrl, String promptText) {
    OllamaApi ollamaApi = OllamaApi.builder().baseUrl(hostUrl).build();

    OllamaChatModel chatModel = OllamaChatModel.builder()
        .ollamaApi(ollamaApi)
        .build();

    ToolCallback[] toolCallbacks = ToolCallbacks.from(new SetupQueryTool(setupsService));

    List<Message> messages = chatMemory.get(CHAT_CONVERSATION_ID);
    if (messages.isEmpty()) {
      messages = new ArrayList<>();
    }
    UserMessage userMessage = new UserMessage(promptText);
    messages.add(userMessage);

    ChatResponse response = chatModel.call(
        new Prompt(
            messages,
            OllamaOptions.builder()
                .toolCallbacks(toolCallbacks)
                .model("qwen3-coder:30b")
                .temperature(0.4)
                .build()
        ));
    StringBuilder sb = new StringBuilder();
    response.getResults().forEach(g -> {
      sb.append(g.getOutput().getText());
    });

    String result = sb.toString();
    chatMemory.add(CHAT_CONVERSATION_ID, List.of(userMessage, new AssistantMessage(result)));

    return result;
  }


  public String getChatInitMessage() {
    SetupIniFileScanStats stats = setupsService.getStats();
    if (stats.isScanDone()) {
      StringBuilder sb = new StringBuilder();
      sb.append(stats);
      return sb.toString();
    } else {
      return "[STATS NOT AVAILABLE]";
    }
  }

}
