package com.airiot.fi.model.difference;

import java.util.List;

public class SetupDifferenceGroup {

  private String setupGroupName;
  private List<SetupDifference> setupDifferences;

  public String getSetupGroupName() {
    return setupGroupName;
  }

  public void setSetupGroupName(String setupGroupName) {
    this.setupGroupName = setupGroupName;
  }

  public List<SetupDifference> getSetupDifferences() {
    return setupDifferences;
  }

  public void setSetupDifferences(List<SetupDifference> setupDifferences) {
    this.setupDifferences = setupDifferences;
  }
}
