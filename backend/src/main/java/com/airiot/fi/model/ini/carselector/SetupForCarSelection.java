package com.airiot.fi.model.ini.carselector;

public class SetupForCarSelection {

  private long id;

  private String carFolderName;
  private String trackFolderName;
  private String setupIniFileName;


  public SetupForCarSelection(long id, String setupIniFileName) {
    this.id = id;
    this.setupIniFileName = setupIniFileName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCarFolderName() {
    return carFolderName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public String getTrackFolderName() {
    return trackFolderName;
  }

  public void setTrackFolderName(String trackFolderName) {
    this.trackFolderName = trackFolderName;
  }

  public String getSetupIniFileName() {
    return setupIniFileName;
  }

  public void setSetupIniFileName(String setupIniFileName) {
    this.setupIniFileName = setupIniFileName;
  }

  public String toString() {
    return "SetupForCarSelection(id=" + this.getId() + ", setupIniFileName=" + this.getSetupIniFileName() + ")";
  }

}
