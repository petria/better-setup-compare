package com.airiot.fi.controller;

import com.airiot.fi.service.model.SetupsService;
import com.airiot.fi.service.model.carselector.CarForSelection;
import com.airiot.fi.service.model.carselector.SetupForCarSelection;
import com.airiot.fi.service.model.carselector.TrackForCarSelection;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public List<String> getSetupById(@PathVariable long setupId) {
    return setupsService.getSetupById(setupId);
  }

}
