package com.airiot.fi.service.ai;

import com.airiot.fi.model.ai.OllamaServerConfig;
import com.airiot.fi.model.ini.scan.SetupIniFileScanStats;
import com.airiot.fi.service.SetupsService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiConfigsService {

  private static final Logger log = LoggerFactory.getLogger(AiConfigsService.class);

  private final SetupsService setupsService;

  private final ConfiguredOllamaServers configuredOllamaServers;

  public AiConfigsService(SetupsService setupsService, ConfiguredOllamaServers configuredOllamaServers) {
    this.setupsService = setupsService;
    this.configuredOllamaServers = configuredOllamaServers;
  }


  @PostConstruct
  public void initService() {

    for (OllamaServerConfig serverConfig : configuredOllamaServers.ollamaServerConfigs()) {
      OllamaApi ollamaApi = OllamaApi.builder().baseUrl(serverConfig.getServerUrl()).build();
      try {
        OllamaApi.ListModelResponse response = ollamaApi.listModels();
        serverConfig.getAvailableModelNames().clear();
        response.models().forEach(ollamaModel -> {
          serverConfig.getAvailableModelNames().add(ollamaModel.name());
        });
        serverConfig.setStatus("OK: " + System.currentTimeMillis());
      } catch (Exception e) {
        String error = e.toString();
        log.error("Error in init service: {}", error);
        serverConfig.setStatus("ERROR: " + error);
      }

    }

  }

  public String handleCommand(String command) {
    String[] parts = command.split(" ", 2);
    String commandName = parts[0];

    switch (commandName) {
      case "/help":
        return "Available commands: \n/help - Shows this help message";
      default:
        return "Unknown command: " + commandName;
    }
  }


  public String getChatInitMessage() {
    SetupIniFileScanStats stats = setupsService.getStats();
    if (stats.isScanDone()) {
      StringBuilder sb = new StringBuilder();
      sb.append(stats);
      return sb.toString();
    } else {
      return "[STATS NOT AVAILABLE]";
    }
  }

  public List<OllamaServerConfig> getOllamaServerConfigs() {
    return this.configuredOllamaServers.ollamaServerConfigs();
  }

}
