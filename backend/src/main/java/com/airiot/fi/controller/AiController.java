package com.airiot.fi.controller;

import com.airiot.fi.model.ai.OllamaServerConfig;
import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.service.ai.AiService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AiController.class);
  private final AiService aiService;

  public AiController(AiService aiService) {
    this.aiService = aiService;
  }

  @PostMapping("/chat")
  public String aiChat(@RequestBody AiChatRequest request) {
    log.debug("Received aiChat: {}", request);
//    String response = aiService.getAiChatResponse(request.getPrompt());
//    String response = aiService.aiChat("http://localhost:11434", request.getPrompt());
    String response = aiService.aiChat("http://192.168.0.111:11434", request.getPrompt());
//    log.debug("response: {}",response);
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
