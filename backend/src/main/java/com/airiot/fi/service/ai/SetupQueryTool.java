package com.airiot.fi.service.ai;

import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;


public class SetupQueryTool {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SetupQueryTool.class);


  @Tool(description = "Return the raw contents of an Assetto Corsa setup file for given setup id")
  public String getSetupByIdAsString(@ToolParam(description = "Setup internal id number") long id) {
    log.debug("tool called: getSetupByIdAsString({})", id);
    return "setup for id: " + id;
  }

}
