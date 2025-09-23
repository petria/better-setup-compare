package com.airiot.fi.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiSpringConfig {


  @Bean
  public ChatClient ollamaChatClient(OllamaChatModel chatModel) {
    ChatMemory chatMemory
        = MessageWindowChatMemory.builder()
        .maxMessages(1000)
        .build();
    ChatClient.Builder builder = ChatClient.builder(chatModel);
    ChatClient client =
        builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())

            .build();
    return client;
  }

}
