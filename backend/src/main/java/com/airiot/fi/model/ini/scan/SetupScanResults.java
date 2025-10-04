package com.airiot.fi.model.ini.scan;


import java.util.List;
import java.util.Map;

public class SetupScanResults {

  String carFolder;
  String trackFolder;

  private Map<String, String> iniFilesMap;
  private Map<Long, SetupIniFile> setupIdToIniFileMap;

  SetupScanResults(String carFolder, String trackFolder, Map<String, String> iniFilesMap, Map<Long, SetupIniFile> setupIdToIniFileMap) {
    this.carFolder = carFolder;
    this.trackFolder = trackFolder;
    this.iniFilesMap = iniFilesMap;
    this.setupIdToIniFileMap = setupIdToIniFileMap;
  }

  public static SetupScanResultsBuilder builder() {
    return new SetupScanResultsBuilder();
  }

  public String getCarFolder() {
    return this.carFolder;
  }

  public String getTrackFolder() {
    return this.trackFolder;
  }

  public Map<String, String> getIniFilesMap() {
    return this.iniFilesMap;
  }

  public Map<Long, SetupIniFile> getSetupIdToIniFileMap() {
    return setupIdToIniFileMap;
  }

  public void setCarFolder(String carFolder) {
    this.carFolder = carFolder;
  }

  public void setTrackFolder(String trackFolder) {
    this.trackFolder = trackFolder;
  }

  public void setIniFilesMap(Map<String, String> iniFilesMap) {
    this.iniFilesMap = iniFilesMap;
  }

  public void setSetupIdToIniFileMap(Map<Long, SetupIniFile> setupIdToIniFileMap) {
    this.setupIdToIniFileMap = setupIdToIniFileMap;
  }

  public String toString() {
    return "SetupScanResults(carFolder=" + this.getCarFolder() + ", trackFolder=" + this.getTrackFolder() + ", iniFilesMap=" + this.getIniFilesMap() + ")";
  }

  public static class SetupScanResultsBuilder {
    private String carFolder;
    private String trackFolder;
    private List<String> iniFilePath;
    private Map<String, String> iniFilesMap;
    private Map<Long, SetupIniFile> setupIdToIniFileMap;

    SetupScanResultsBuilder() {
    }

    public SetupScanResultsBuilder carFolder(String carFolder) {
      this.carFolder = carFolder;
      return this;
    }

    public SetupScanResultsBuilder trackFolder(String trackFolder) {
      this.trackFolder = trackFolder;
      return this;
    }

    public SetupScanResultsBuilder iniFilePath(List<String> iniFilePath) {
      this.iniFilePath = iniFilePath;
      return this;
    }

    public SetupScanResultsBuilder iniFilesMap(Map<String, String> iniFilesMap) {
      this.iniFilesMap = iniFilesMap;
      return this;
    }

    public SetupScanResultsBuilder setupIdToIniFileMap(Map<Long, SetupIniFile> setupIdToIniFileMap) {
      this.setupIdToIniFileMap = setupIdToIniFileMap;
      return this;
    }


    public SetupScanResults build() {
      return new SetupScanResults(this.carFolder, this.trackFolder, this.iniFilesMap, this.setupIdToIniFileMap);
    }

    public String toString() {
      return "SetupScanResults.SetupScanResultsBuilder(carFolder=" + this.carFolder + ", trackFolder=" + this.trackFolder + ", iniFilePath=" + this.iniFilePath + ", iniFilesMap=" + this.iniFilesMap + ")";
    }
  }
}
