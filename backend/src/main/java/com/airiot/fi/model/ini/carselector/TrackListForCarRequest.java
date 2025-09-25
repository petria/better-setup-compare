package com.airiot.fi.model.ini.carselector;


public class TrackListForCarRequest {

  private String carFolderName;

  public TrackListForCarRequest(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public TrackListForCarRequest() {
  }

  public static TrackListForCarRequestBuilder builder() {
    return new TrackListForCarRequestBuilder();
  }

  public String getCarFolderName() {
    return this.carFolderName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof TrackListForCarRequest)) return false;
    final TrackListForCarRequest other = (TrackListForCarRequest) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$carFolderName = this.getCarFolderName();
    final Object other$carFolderName = other.getCarFolderName();
    if (this$carFolderName == null ? other$carFolderName != null : !this$carFolderName.equals(other$carFolderName))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof TrackListForCarRequest;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $carFolderName = this.getCarFolderName();
    result = result * PRIME + ($carFolderName == null ? 43 : $carFolderName.hashCode());
    return result;
  }

  public String toString() {
    return "TrackListForCarRequest(carFolderName=" + this.getCarFolderName() + ")";
  }

  public static class TrackListForCarRequestBuilder {
    private String carFolderName;

    TrackListForCarRequestBuilder() {
    }

    public TrackListForCarRequestBuilder carFolderName(String carFolderName) {
      this.carFolderName = carFolderName;
      return this;
    }

    public TrackListForCarRequest build() {
      return new TrackListForCarRequest(this.carFolderName);
    }

    public String toString() {
      return "TrackListForCarRequest.TrackListForCarRequestBuilder(carFolderName=" + this.carFolderName + ")";
    }
  }
}
