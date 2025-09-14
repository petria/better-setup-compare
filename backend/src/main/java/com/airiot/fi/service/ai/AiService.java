package com.airiot.fi.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiService {

  private final ChatClient chatClient;

  public AiService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public Flux<String> aiChatStream(String prompt) {
    return chatClient.prompt()
        .system("You are helpful Assetto Corsa Sim Racing game expert that can analyze game car setup and give characteristic how setup effects car behaviour. When given multiple saved setup ini files you can analyze the differences between setups and  what is their difference in car driving.")
        .user(prompt)
        .stream()
        .content();

  }
}
