package com.airiot.fi.controller;

import com.airiot.fi.model.api.AiChatRequest;
import com.airiot.fi.service.ai.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
@Slf4j
public class AiController {

  private final AiService aiService;

  public AiController(AiService aiService) {
    this.aiService = aiService;
  }

  @PostMapping("/stream/chat")
  public Flux<String> aiChatStream(@RequestBody AiChatRequest request) {
    return aiService.aiChatStream(request.getPrompt());
  }

}
