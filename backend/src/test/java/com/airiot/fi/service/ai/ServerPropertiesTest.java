package com.airiot.fi.service.ai;

import com.airiot.fi.model.ai.OllamaServerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ServerPropertiesTest {

  @Test
  void parseServerConfig() {
    ServerProperties serverProperties = new ServerProperties(getMockData());

    List<OllamaServerConfig> parsed = serverProperties.parseServerConfig();
    Assertions.assertEquals(3, parsed.size());

  }


  // setups.servers=http://localhost:11434|5900x+rtx3900,http://192.168.0.111::11434|MacBook M2 max,http://192.168.0.143:11434|9950x3d+rtx5900
  private List<String> getMockData() {
    return List.of(
        "http://localhost:11434|5900x+rtx3900",
        "http://192.168.0.111::11434|MacBook M2 max",
        "http://192.168.0.143:11434|9950x3d+rtx5900");
  }
}