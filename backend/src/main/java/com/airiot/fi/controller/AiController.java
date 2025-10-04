package com.airiot.fi.controller;

import com.airiot.fi.model.ai.OllamaServerConfig;
import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.service.ai.AiService;
import com.airiot.fi.service.ai.AiServiceDyn;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AiController.class);
  private final AiService aiService;
  private final AiServiceDyn aiServiceDyn;

  public AiController(AiService aiService, AiServiceDyn aiServiceDyn) {
    this.aiService = aiService;
    this.aiServiceDyn = aiServiceDyn;
  }

  @PostMapping("/chat")
  public String aiChat(@RequestBody AiChatRequest request) {
    log.debug("Received aiChat: {}", request);
    //String response = aiService.aiChat(request.getServerUrl(), request.getPrompt());
    String response = aiServiceDyn.ask(request.getServerUrl(),"qwen3:30b-a3b" ,request.getPrompt());
    return response;
  }

  @GetMapping("/getChatInitMessage")
  public String getChatInitMessage() {
    return aiService.getChatInitMessage();
  }


  @GetMapping("/getOllamaServerConfigs")
  public List<OllamaServerConfig> getConfiguredOllamaServers() {
    return aiService.getOllamaServerConfigs();
  }

}
