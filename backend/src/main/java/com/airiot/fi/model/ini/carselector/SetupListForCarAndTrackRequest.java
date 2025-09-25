package com.airiot.fi.model.ini.carselector;


public class SetupListForCarAndTrackRequest {

  private String carFolderName;

  private String trackFolderName;

  public SetupListForCarAndTrackRequest(String carFolderName, String trackFolderName) {
    this.carFolderName = carFolderName;
    this.trackFolderName = trackFolderName;
  }

  public SetupListForCarAndTrackRequest() {
  }

  public static SetupListForCarAndTrackRequestBuilder builder() {
    return new SetupListForCarAndTrackRequestBuilder();
  }

  public String getCarFolderName() {
    return this.carFolderName;
  }

  public String getTrackFolderName() {
    return this.trackFolderName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public void setTrackFolderName(String trackFolderName) {
    this.trackFolderName = trackFolderName;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupListForCarAndTrackRequest)) return false;
    final SetupListForCarAndTrackRequest other = (SetupListForCarAndTrackRequest) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$carFolderName = this.getCarFolderName();
    final Object other$carFolderName = other.getCarFolderName();
    if (this$carFolderName == null ? other$carFolderName != null : !this$carFolderName.equals(other$carFolderName))
      return false;
    final Object this$trackFolderName = this.getTrackFolderName();
    final Object other$trackFolderName = other.getTrackFolderName();
    if (this$trackFolderName == null ? other$trackFolderName != null : !this$trackFolderName.equals(other$trackFolderName))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupListForCarAndTrackRequest;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $carFolderName = this.getCarFolderName();
    result = result * PRIME + ($carFolderName == null ? 43 : $carFolderName.hashCode());
    final Object $trackFolderName = this.getTrackFolderName();
    result = result * PRIME + ($trackFolderName == null ? 43 : $trackFolderName.hashCode());
    return result;
  }

  public String toString() {
    return "SetupListForCarAndTrackRequest(carFolderName=" + this.getCarFolderName() + ", trackFolderName=" + this.getTrackFolderName() + ")";
  }

  public static class SetupListForCarAndTrackRequestBuilder {
    private String carFolderName;
    private String trackFolderName;

    SetupListForCarAndTrackRequestBuilder() {
    }

    public SetupListForCarAndTrackRequestBuilder carFolderName(String carFolderName) {
      this.carFolderName = carFolderName;
      return this;
    }

    public SetupListForCarAndTrackRequestBuilder trackFolderName(String trackFolderName) {
      this.trackFolderName = trackFolderName;
      return this;
    }

    public SetupListForCarAndTrackRequest build() {
      return new SetupListForCarAndTrackRequest(this.carFolderName, this.trackFolderName);
    }

    public String toString() {
      return "SetupListForCarAndTrackRequest.SetupListForCarAndTrackRequestBuilder(carFolderName=" + this.carFolderName + ", trackFolderName=" + this.trackFolderName + ")";
    }
  }
}
