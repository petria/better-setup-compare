package com.airiot.fi.service.ai;

import com.airiot.fi.model.ini.scan.SetupIniFileScanStats;
import com.airiot.fi.service.SetupsService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class AiService {

  private static final String SYSTEM_PROMPT = "You are helpful Assetto Corsa Sim Racing game expert that can analyze game car setup and give characteristic how setup effects car behaviour. When given multiple saved setup ini files you can analyze the differences between setups and  what is their difference in car driving.";

  private final SetupsService setupsService;

  private final ChatClient chatClient;

  public AiService(SetupsService setupsService, ChatClient chatClient) {
    this.setupsService = setupsService;
    this.chatClient = chatClient;
  }

  public Flux<String> aiChatStream(String prompt) {
    return chatClient.prompt()
        .tools(new SetupQueryTool(setupsService))
        .user(prompt)
        .system(SYSTEM_PROMPT)
        .stream()
        .content();
  }

  public String getAiChatResponse(String prompt) {
    String reply
        = chatClient.prompt(prompt)
        .tools(new SetupQueryTool(setupsService))
        .system(SYSTEM_PROMPT)
        .call()
        .content();

    return reply;
  }

  /**
   * Sends the given prompt to the Ollama server specified by hostUrl
   * and returns the model's text output.
   *
   * @param hostUrl  Base URL of the Ollama host, e.g. "http://localhost:11434"
   * @param promptText Prompt text to send
   * @return Model output text
   */
  public String aiChat(String hostUrl, String promptText) {
    // Create a fresh API client for the specified host
    String modelName = "llama3";   // or dynamically choose/receive as parameter

    OllamaApi ollamaApi = OllamaApi.builder().baseUrl(hostUrl).build();
    ModelManagementOptions modelManagementOptions = ModelManagementOptions.builder()
        .additionalModels(List.of(modelName))
        .build();

    OllamaChatModel chatModel = OllamaChatModel.builder()
        .ollamaApi(ollamaApi)
        .modelManagementOptions(modelManagementOptions)
        .build();

    ChatClient client = ChatClient.create(chatModel);
//    ChatResponse chatResponse = client.prompt().tools(new SetupQueryTool(setupsService)).system(SYSTEM_PROMPT).user(promptText).call().chatResponse();

    String result = client.prompt().tools(new SetupQueryTool(setupsService)).system(SYSTEM_PROMPT).user(promptText).call().content();

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
