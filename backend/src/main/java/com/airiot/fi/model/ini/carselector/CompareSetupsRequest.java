package com.airiot.fi.model.ini.carselector;


import java.util.List;

public class CompareSetupsRequest {

  private List<Long> setupIds;

  public List<Long> getSetupIds() {
    return setupIds;
  }

  public void setSetupIds(List<Long> setupIds) {
    this.setupIds = setupIds;
  }

  @Override
  public String toString() {
    return "CompareSetupsRequest{" +
        "setupIds=" + setupIds +
        '}';
  }
}
