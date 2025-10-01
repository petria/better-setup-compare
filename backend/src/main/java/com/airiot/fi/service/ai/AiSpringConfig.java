package com.airiot.fi.service.ai;

import com.airiot.fi.model.ai.OllamaServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiSpringConfig {


  @Bean
  public ConfiguredOllamaServers configuredOllamaServers(ServerProperties serverProperties) {
    List<OllamaServerConfig> ollamaServerConfigs = serverProperties.parseServerConfig();
    ConfiguredOllamaServers configuredOllamaServers = new ConfiguredOllamaServers(ollamaServerConfigs);
    return configuredOllamaServers;
  }

}
