package com.airiot.fi.model.difference;

import java.util.List;
import java.util.Map;

public class CarSetupDifferences {

  private Long setupId;
  private String carFolderName;

  private Map<String, String> iniValues;
  private List<SetupDifferenceGroup> setupDifferenceGroups;

  public Long getSetupId() {
    return setupId;
  }

  public void setSetupId(Long setupId) {
    this.setupId = setupId;
  }

  public String getCarFolderName() {
    return carFolderName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public Map<String, String> getIniValues() {
    return iniValues;
  }

  public void setIniValues(Map<String, String> iniValues) {
    this.iniValues = iniValues;
  }

  public List<SetupDifferenceGroup> getSetupDifferenceGroups() {
    return setupDifferenceGroups;
  }

  public void setSetupDifferenceGroups(List<SetupDifferenceGroup> setupDifferenceGroups) {
    this.setupDifferenceGroups = setupDifferenceGroups;
  }
}
