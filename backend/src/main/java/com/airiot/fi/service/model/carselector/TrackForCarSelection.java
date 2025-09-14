package com.airiot.fi.service.model.carselector;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackForCarSelection {

  private long id;

  private String trackName;

  private String trackFolderName;

  private int iniFileCount;

}
