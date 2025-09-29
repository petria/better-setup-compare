package com.airiot.fi.service.ai;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "setups")
public class ServerProperties {

  private List<String> servers;

  public List<String> getServers() {
    return servers;
  }

  public void setServers(List<String> servers) {
    this.servers = servers;
  }
}
