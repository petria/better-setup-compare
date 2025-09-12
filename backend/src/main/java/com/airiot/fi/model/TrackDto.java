package com.airiot.fi.model;

import java.util.List;

public class TrackDto {
  private long id;
  private String name;
  private List<SetupDto> setupDtos;

  public TrackDto(String name, List<SetupDto> setupDtos) {
    this.name = name;
    this.setupDtos = setupDtos;
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

  public List<SetupDto> getSetups() {
    return setupDtos;
  }

  public void setSetups(List<SetupDto> setupDtos) {
    this.setupDtos = setupDtos;
  }
}
