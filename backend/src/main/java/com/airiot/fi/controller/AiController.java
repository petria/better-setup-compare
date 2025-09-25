package com.airiot.fi.controller;

import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.service.ai.AiService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AiController.class);
  private final AiService aiService;

  public AiController(AiService aiService) {
    this.aiService = aiService;
  }

  @PostMapping("/chatStream")
  public Flux<String> aiChatStream(@RequestBody AiChatRequest request) {
    log.debug("Received aiChatStream: {}", request);
    return aiService.aiChatStream(request.getPrompt());
  }

  @PostMapping("/chat")
  public String aiChat(@RequestBody AiChatRequest request) {
    log.debug("Received aiChat: {}", request);
    String response = aiService.getAiChatResponse(request.getPrompt());
//    log.debug("response: {}",response);
    return response;
  }

  @GetMapping("/getChatInitMessage")
  public String getChatInitMessage() {
    return aiService.getChatInitMessage();
  }

}
