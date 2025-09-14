package com.airiot.fi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }


  @Bean
  public ChatClient ollamaChatClient(OllamaChatModel chatModel) {
    ChatMemory chatMemory
        = MessageWindowChatMemory.builder()
        .maxMessages(1000)
        .build();
    ChatClient.Builder builder = ChatClient.builder(chatModel);
    ChatClient client = builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    return client;
  }

}
