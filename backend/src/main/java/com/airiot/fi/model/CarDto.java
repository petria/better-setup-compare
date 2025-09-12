package com.airiot.fi.model;

import java.util.ArrayList;
import java.util.List;


public class CarDto {

  private long id;
  private String name;
  private List<TrackDto> trackDtos = new ArrayList<>();


  public CarDto(String name) {
    this.name = name;
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

  public List<TrackDto> getTracks() {
    return trackDtos;
  }

  public void setTracks(List<TrackDto> trackDtos) {
    this.trackDtos = trackDtos;
  }

}
