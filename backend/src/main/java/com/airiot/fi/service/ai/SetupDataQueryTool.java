package com.airiot.fi.service.ai;

import com.airiot.fi.model.ini.carselector.CarForSelection;
import com.airiot.fi.service.SetupsService;
import org.slf4j.Logger;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;


@SuppressWarnings("ClassCanBeRecord")
public class SetupDataQueryTool {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SetupDataQueryTool.class);

  private final SetupsService setupsService;

  public SetupDataQueryTool(SetupsService setupsService) {
    this.setupsService = setupsService;
  }

  @Tool(description = "Return the raw contents of an Assetto Corsa setup file for given unique setup id")
  public String getSetupContentsById(@ToolParam(description = "Setup id number") long id) {
    log.debug("tool called: getSetupContentsById({})", id);
    String iniFile = setupsService.getSetupById(id);
    return iniFile;
  }

  @Tool(description = "Return car data by unique car id")
  public String getCarById(@ToolParam(description = "Car id number") long id) {
    log.debug("tool called: getCarById({})", id);
    CarForSelection car  = setupsService.getCarById(id);
    String out =
    """
      car unique id: %d\s
      car name: %s\s
      car folder name: %s\s
      how many tracks with setup this car has: %d 
    """.formatted(car.getId(), car.getCarName(), car.getCarFolderName(), car.getCarTracksWithSetup());

    return out;
  }

}
