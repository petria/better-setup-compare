package com.airiot.fi.service.ai;

import com.airiot.fi.model.ai.OllamaServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "setups")
public class ServerProperties {

  public ServerProperties() {
  }

  public ServerProperties(List<String> servers) {
    this.servers = servers;
  }

  private List<String> servers;

  public List<String> getServers() {
    return servers;
  }

  public void setServers(List<String> servers) {
    this.servers = servers;
  }

  public List<OllamaServerConfig> parseServerConfig() {
    List<OllamaServerConfig> serverConfigs = new ArrayList<>();
    long id = 0;
    for (String server : servers) {
      String[] split = server.split("\\|");
      OllamaServerConfig serverConfig = new OllamaServerConfig();
      serverConfig.setId(id);
      serverConfig.setServerUrl(split[0]);
      serverConfig.setServerName(split[1]);
      serverConfigs.add(serverConfig);

      id++;
    }
    return serverConfigs;
  }


}
