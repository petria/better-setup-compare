package com.airiot.fi.controller;

import com.airiot.fi.model.ai.OllamaServerConfig;
import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.model.api.AiCommandRequest;
import com.airiot.fi.service.ai.AiConfigsService;
import com.airiot.fi.service.ai.AiChatService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AiController.class);
  private final AiConfigsService aiConfigsService;
  private final AiChatService aiChatService;

  public AiController(AiConfigsService aiConfigsService, AiChatService aiChatService) {
    this.aiConfigsService = aiConfigsService;
    this.aiChatService = aiChatService;
  }

  @PostMapping("/chat")
  public String aiChat(@RequestBody AiChatRequest request, Principal principal) {
    log.debug("Received aiChat from user '{}': {}", principal.getName(), request);
    String response = aiChatService.ask(principal.getName(), request.getServerUrl(),"qwen3:30b-a3b" ,request.getPrompt());
    return response;
  }

  @PostMapping("/command")
  public String aiCommand(@RequestBody AiCommandRequest request) {
    log.debug("Received aiCommand: {}", request);
    return aiConfigsService.handleCommand(request.getCommand());
  }

  @GetMapping("/getChatInitMessage")
  public String getChatInitMessage() {
    return aiConfigsService.getChatInitMessage();
  }


  @GetMapping("/getOllamaServerConfigs")
  public List<OllamaServerConfig> getConfiguredOllamaServers() {
    return aiConfigsService.getOllamaServerConfigs();
  }

}
