package com.airiot.fi.model.difference;

import java.util.List;

public class CompareSetupsResponse {

  private List<CarSetupDifferences> carSetupDifferences;

  public List<CarSetupDifferences> getCarSetupDifferences() {
    return carSetupDifferences;
  }

  public void setCarSetupDifferences(List<CarSetupDifferences> carSetupDifferences) {
    this.carSetupDifferences = carSetupDifferences;
  }
}
