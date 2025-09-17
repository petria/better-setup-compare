package com.airiot.fi.service.model.carselector;


import java.util.List;

public class TrackListForCarResponse {

  private List<TrackForCarSelection> trackList;

  TrackListForCarResponse(List<TrackForCarSelection> trackList) {
    this.trackList = trackList;
  }

  public static TrackListForCarResponseBuilder builder() {
    return new TrackListForCarResponseBuilder();
  }

  public List<TrackForCarSelection> getTrackList() {
    return this.trackList;
  }

  public void setTrackList(List<TrackForCarSelection> trackList) {
    this.trackList = trackList;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof TrackListForCarResponse)) return false;
    final TrackListForCarResponse other = (TrackListForCarResponse) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$trackList = this.getTrackList();
    final Object other$trackList = other.getTrackList();
    if (this$trackList == null ? other$trackList != null : !this$trackList.equals(other$trackList)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof TrackListForCarResponse;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $trackList = this.getTrackList();
    result = result * PRIME + ($trackList == null ? 43 : $trackList.hashCode());
    return result;
  }

  public String toString() {
    return "TrackListForCarResponse(trackList=" + this.getTrackList() + ")";
  }

  public static class TrackListForCarResponseBuilder {
    private List<TrackForCarSelection> trackList;

    TrackListForCarResponseBuilder() {
    }

    public TrackListForCarResponseBuilder trackList(List<TrackForCarSelection> trackList) {
      this.trackList = trackList;
      return this;
    }

    public TrackListForCarResponse build() {
      return new TrackListForCarResponse(this.trackList);
    }

    public String toString() {
      return "TrackListForCarResponse.TrackListForCarResponseBuilder(trackList=" + this.trackList + ")";
    }
  }
}
