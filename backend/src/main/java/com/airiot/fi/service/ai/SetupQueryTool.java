package com.airiot.fi.service.ai;

import com.airiot.fi.service.model.SetupsService;
import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.stream.Collectors;


public class SetupQueryTool {


  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SetupQueryTool.class);

  private final SetupsService setupsService;

  public SetupQueryTool(SetupsService setupsService) {
    this.setupsService = setupsService;
  }

  @Tool(description = "Return the raw contents of an Assetto Corsa setup file for given setup id")
  public String getSetupByIdAsString(@ToolParam(description = "Setup internal id number") long id) {
    log.debug("tool called: getSetupByIdAsString({})", id);

    String iniFile = setupsService.getSetupById(id).stream().map(s -> s + "\n").collect(Collectors.joining());

    return iniFile;
  }

}
