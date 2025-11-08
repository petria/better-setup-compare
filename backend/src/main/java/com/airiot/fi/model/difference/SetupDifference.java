package com.airiot.fi.model.difference;

public class SetupDifference {

  private String configKey;
  private String configValue;
  private String configDifference;

  public SetupDifference(String configKey, String configValue, String configDifference) {
    this.configKey = configKey;
    this.configValue = configValue;
    this.configDifference = configDifference;
  }

  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  public String getConfigDifference() {
    return configDifference;
  }

  public void setConfigDifference(String configDifference) {
    this.configDifference = configDifference;
  }

  @Override
  public String toString() {
    return "SetupDifference{" +
        "configKey='" + configKey + '\'' +
        ", configValue='" + configValue + '\'' +
        ", configDifference='" + configDifference + '\'' +
        '}';
  }
}
