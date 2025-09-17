package com.airiot.fi.service.model.scan;

import java.util.HashMap;
import java.util.Map;

public class Track {

  private long id;

  private String trackName;

  private String trackFolderName;

  private Map<String, String> iniFilesMap = new HashMap<>();


  public Track() {
  }

  public long getId() {
    return this.id;
  }

  public String getTrackName() {
    return this.trackName;
  }

  public String getTrackFolderName() {
    return this.trackFolderName;
  }

  public Map<String, String> getIniFilesMap() {
    return this.iniFilesMap;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }

  public void setTrackFolderName(String trackFolderName) {
    this.trackFolderName = trackFolderName;
  }

  public void setIniFilesMap(Map<String, String> iniFilesMap) {
    this.iniFilesMap = iniFilesMap;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Track)) return false;
    final Track other = (Track) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$trackName = this.getTrackName();
    final Object other$trackName = other.getTrackName();
    if (this$trackName == null ? other$trackName != null : !this$trackName.equals(other$trackName)) return false;
    final Object this$trackFolderName = this.getTrackFolderName();
    final Object other$trackFolderName = other.getTrackFolderName();
    if (this$trackFolderName == null ? other$trackFolderName != null : !this$trackFolderName.equals(other$trackFolderName))
      return false;
    final Object this$iniFilesMap = this.getIniFilesMap();
    final Object other$iniFilesMap = other.getIniFilesMap();
    if (this$iniFilesMap == null ? other$iniFilesMap != null : !this$iniFilesMap.equals(other$iniFilesMap))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Track;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $id = this.getId();
    result = result * PRIME + (int) ($id >>> 32 ^ $id);
    final Object $trackName = this.getTrackName();
    result = result * PRIME + ($trackName == null ? 43 : $trackName.hashCode());
    final Object $trackFolderName = this.getTrackFolderName();
    result = result * PRIME + ($trackFolderName == null ? 43 : $trackFolderName.hashCode());
    final Object $iniFilesMap = this.getIniFilesMap();
    result = result * PRIME + ($iniFilesMap == null ? 43 : $iniFilesMap.hashCode());
    return result;
  }

  public String toString() {
    return "Track(id=" + this.getId() + ", trackName=" + this.getTrackName() + ", trackFolderName=" + this.getTrackFolderName() + ", iniFilesMap=" + this.getIniFilesMap() + ")";
  }
}
