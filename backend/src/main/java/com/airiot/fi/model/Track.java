package com.airiot.fi.model;

import java.util.List;

public class Track {
  private long id;
  private String name;
  private List<Setup> setups;

  public Track(String name, List<Setup> setups) {
    this.name = name;
    this.setups = setups;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Setup> getSetups() {
    return setups;
  }

  public void setSetups(List<Setup> setups) {
    this.setups = setups;
  }
}
