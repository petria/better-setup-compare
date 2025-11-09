package com.airiot.fi.controller;

import com.airiot.fi.model.difference.CompareSetupsRequest;
import com.airiot.fi.model.difference.CompareSetupsResponse;
import com.airiot.fi.model.ini.carselector.*;
import com.airiot.fi.service.SetupsService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {


  private static final Logger log = org.slf4j.LoggerFactory.getLogger(DataController.class);
  private final SetupsService setupsService;

  public DataController(SetupsService setupsService) {
    this.setupsService = setupsService;
  }


  @GetMapping("/cars")
  public List<CarForSelection> getCars() {
    return setupsService.getCarListForSelection();
  }

  @GetMapping("/cars/{carFolderName}/tracks")
  public List<TrackForCarSelection> getTracks(@PathVariable String carFolderName) {
    System.out.println("getTracks called with carFolderName: " + carFolderName);
    return setupsService.getTrackByFolderName(carFolderName);
  }

  @GetMapping("/cars/{carFolderName}/tracks/{trackFolderName}/setups")
  public List<SetupForCarSelection> getSetups(@PathVariable String carFolderName, @PathVariable String trackFolderName) {
    return setupsService.getSetupListForCarAndTrack(carFolderName, trackFolderName);
  }

  @GetMapping("/setups/{setupId}")
  public String getSetupById(@PathVariable long setupId) {
    return setupsService.getSetupById(setupId);
  }

  @PostMapping("/setups/compare")
  public CompareSetupsResponse compareSetups(@RequestBody CompareSetupsRequest request) throws IOException {
    log.debug("compareSetups called with request: {}", request);
    return setupsService.compareSetups(request.getSetupIds());
  }
}
