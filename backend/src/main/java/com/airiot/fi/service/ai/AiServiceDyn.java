package com.airiot.fi.service.ai;


import com.airiot.fi.service.SetupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AiServiceDyn {

  private static final String SYSTEM_PROMPT = "You are helpful Assetto Corsa Sim Racing game expert that can analyze game car setup and give characteristic how setup effects car behaviour. When given multiple saved setup ini files you can analyze the differences between setups and  what is their difference in car driving.";
  private static final Logger log = LoggerFactory.getLogger(AiServiceDyn.class);

  private final SetupsService setupsService;
  private final OllamaClientFactory factory;

  public AiServiceDyn(SetupsService setupsService, OllamaClientFactory factory) {
    this.setupsService = setupsService;
    this.factory = factory;
  }

  public String ask(String hostUrl, String modelName, String promptText) {
    ChatClient client = factory.createClient(hostUrl, modelName);

    StringBuilder sb = new StringBuilder();
    long start = System.currentTimeMillis();
    log.debug("Sending chat request...");
    try {
      ChatResponse response = client.prompt(getPrompt(modelName, promptText)).call().chatResponse();
      Objects.requireNonNull(response).getResults().forEach(g -> {
        sb.append(g.getOutput().getText());
      });

    } catch (Exception e) {
      log.error("Error while sending chat request: {}", e.getMessage());
      sb.append("ERROR: ").append(e.getMessage());
    }

    long end = System.currentTimeMillis();
    long duration = end - start;
    log.debug("Done sending chat request, handle took {} ms", duration);


    return sb.toString();
  }

  private Prompt getPrompt(String modelName, String promptText) {
    ToolCallback[] toolCallbacks = ToolCallbacks.from(new SetupQueryTool(setupsService));

    List<Message>  messages = new ArrayList<>();
    SystemMessage systemMessage = new SystemMessage(SYSTEM_PROMPT);
    messages.add(systemMessage);

    UserMessage userMessage = new UserMessage(promptText);
    messages.add(userMessage);


    Prompt prompt = new Prompt(messages,  OllamaOptions.builder()
        .toolCallbacks(toolCallbacks)
        .model(modelName) // "qwen3:30b-a3b"
        .temperature(0.4)
        .build()
    );

    return prompt;

  }

}