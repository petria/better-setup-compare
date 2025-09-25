package com.airiot.fi.model.ini.carselector;


public class TrackForCarSelection {

  private long id;

  private String trackName;

  private String trackFolderName;

  private int iniFileCount;

  TrackForCarSelection(long id, String trackName, String trackFolderName, int iniFileCount) {
    this.id = id;
    this.trackName = trackName;
    this.trackFolderName = trackFolderName;
    this.iniFileCount = iniFileCount;
  }

  public static TrackForCarSelectionBuilder builder() {
    return new TrackForCarSelectionBuilder();
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

  public int getIniFileCount() {
    return this.iniFileCount;
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

  public void setIniFileCount(int iniFileCount) {
    this.iniFileCount = iniFileCount;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof TrackForCarSelection)) return false;
    final TrackForCarSelection other = (TrackForCarSelection) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$trackName = this.getTrackName();
    final Object other$trackName = other.getTrackName();
    if (this$trackName == null ? other$trackName != null : !this$trackName.equals(other$trackName)) return false;
    final Object this$trackFolderName = this.getTrackFolderName();
    final Object other$trackFolderName = other.getTrackFolderName();
    if (this$trackFolderName == null ? other$trackFolderName != null : !this$trackFolderName.equals(other$trackFolderName))
      return false;
    if (this.getIniFileCount() != other.getIniFileCount()) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof TrackForCarSelection;
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
    result = result * PRIME + this.getIniFileCount();
    return result;
  }

  public String toString() {
    return "TrackForCarSelection(id=" + this.getId() + ", trackName=" + this.getTrackName() + ", trackFolderName=" + this.getTrackFolderName() + ", iniFileCount=" + this.getIniFileCount() + ")";
  }

  public static class TrackForCarSelectionBuilder {
    private long id;
    private String trackName;
    private String trackFolderName;
    private int iniFileCount;

    TrackForCarSelectionBuilder() {
    }

    public TrackForCarSelectionBuilder id(long id) {
      this.id = id;
      return this;
    }

    public TrackForCarSelectionBuilder trackName(String trackName) {
      this.trackName = trackName;
      return this;
    }

    public TrackForCarSelectionBuilder trackFolderName(String trackFolderName) {
      this.trackFolderName = trackFolderName;
      return this;
    }

    public TrackForCarSelectionBuilder iniFileCount(int iniFileCount) {
      this.iniFileCount = iniFileCount;
      return this;
    }

    public TrackForCarSelection build() {
      return new TrackForCarSelection(this.id, this.trackName, this.trackFolderName, this.iniFileCount);
    }

    public String toString() {
      return "TrackForCarSelection.TrackForCarSelectionBuilder(id=" + this.id + ", trackName=" + this.trackName + ", trackFolderName=" + this.trackFolderName + ", iniFileCount=" + this.iniFileCount + ")";
    }
  }
}
