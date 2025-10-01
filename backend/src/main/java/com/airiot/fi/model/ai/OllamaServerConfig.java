package com.airiot.fi.model.ai;

import java.util.ArrayList;
import java.util.List;

public class OllamaServerConfig {

  private long id;
  private String serverUrl;
  private String serverName;

  private String status;

  private final List<String> availableModelNames = new ArrayList<>();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<String> getAvailableModelNames() {
    return availableModelNames;
  }

  public void setAvailableModelNames(List<String> availableModelNames) {
    this.availableModelNames.clear();
    this.availableModelNames.addAll(availableModelNames);
  }

}
