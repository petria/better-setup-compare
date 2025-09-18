package com.airiot.fi.controller;

import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.service.ai.AiService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AiController.class);
  private final AiService aiService;

  public AiController(AiService aiService) {
    this.aiService = aiService;
  }

  @PostMapping("/chat")
  public Flux<String> aiChatStream(@RequestBody AiChatRequest request) {
    log.debug("Received AiChatRequest: {}", request);
    return aiService.aiChatStream(request.getPrompt());
  }

}
