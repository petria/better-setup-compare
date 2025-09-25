package com.airiot.fi.model.ini.scan;


import java.util.List;
import java.util.Map;

public class SetupScanResults {

  String carFolder;
  String trackFolder;

  List<String> iniFilePath;
  private Map<String, String> iniFilesMap;

  SetupScanResults(String carFolder, String trackFolder, List<String> iniFilePath, Map<String, String> iniFilesMap) {
    this.carFolder = carFolder;
    this.trackFolder = trackFolder;
    this.iniFilePath = iniFilePath;
    this.iniFilesMap = iniFilesMap;
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

  public List<String> getIniFilePath() {
    return this.iniFilePath;
  }

  public Map<String, String> getIniFilesMap() {
    return this.iniFilesMap;
  }

  public void setCarFolder(String carFolder) {
    this.carFolder = carFolder;
  }

  public void setTrackFolder(String trackFolder) {
    this.trackFolder = trackFolder;
  }

  public void setIniFilePath(List<String> iniFilePath) {
    this.iniFilePath = iniFilePath;
  }

  public void setIniFilesMap(Map<String, String> iniFilesMap) {
    this.iniFilesMap = iniFilesMap;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupScanResults)) return false;
    final SetupScanResults other = (SetupScanResults) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$carFolder = this.getCarFolder();
    final Object other$carFolder = other.getCarFolder();
    if (this$carFolder == null ? other$carFolder != null : !this$carFolder.equals(other$carFolder)) return false;
    final Object this$trackFolder = this.getTrackFolder();
    final Object other$trackFolder = other.getTrackFolder();
    if (this$trackFolder == null ? other$trackFolder != null : !this$trackFolder.equals(other$trackFolder))
      return false;
    final Object this$iniFilePath = this.getIniFilePath();
    final Object other$iniFilePath = other.getIniFilePath();
    if (this$iniFilePath == null ? other$iniFilePath != null : !this$iniFilePath.equals(other$iniFilePath))
      return false;
    final Object this$iniFilesMap = this.getIniFilesMap();
    final Object other$iniFilesMap = other.getIniFilesMap();
    if (this$iniFilesMap == null ? other$iniFilesMap != null : !this$iniFilesMap.equals(other$iniFilesMap))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupScanResults;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $carFolder = this.getCarFolder();
    result = result * PRIME + ($carFolder == null ? 43 : $carFolder.hashCode());
    final Object $trackFolder = this.getTrackFolder();
    result = result * PRIME + ($trackFolder == null ? 43 : $trackFolder.hashCode());
    final Object $iniFilePath = this.getIniFilePath();
    result = result * PRIME + ($iniFilePath == null ? 43 : $iniFilePath.hashCode());
    final Object $iniFilesMap = this.getIniFilesMap();
    result = result * PRIME + ($iniFilesMap == null ? 43 : $iniFilesMap.hashCode());
    return result;
  }

  public String toString() {
    return "SetupScanResults(carFolder=" + this.getCarFolder() + ", trackFolder=" + this.getTrackFolder() + ", iniFilePath=" + this.getIniFilePath() + ", iniFilesMap=" + this.getIniFilesMap() + ")";
  }

  public static class SetupScanResultsBuilder {
    private String carFolder;
    private String trackFolder;
    private List<String> iniFilePath;
    private Map<String, String> iniFilesMap;

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

    public SetupScanResults build() {
      return new SetupScanResults(this.carFolder, this.trackFolder, this.iniFilePath, this.iniFilesMap);
    }

    public String toString() {
      return "SetupScanResults.SetupScanResultsBuilder(carFolder=" + this.carFolder + ", trackFolder=" + this.trackFolder + ", iniFilePath=" + this.iniFilePath + ", iniFilesMap=" + this.iniFilesMap + ")";
    }
  }
}
