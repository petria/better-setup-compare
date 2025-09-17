package com.airiot.fi.service.model.carselector;


import java.util.List;

public class SetupListForCarAndTrackResponse {

  private List<SetupForCarSelection> setupList;

  public SetupListForCarAndTrackResponse(List<SetupForCarSelection> setupList) {
    this.setupList = setupList;
  }

  public SetupListForCarAndTrackResponse() {
  }

  public static SetupListForCarAndTrackResponseBuilder builder() {
    return new SetupListForCarAndTrackResponseBuilder();
  }

  public List<SetupForCarSelection> getSetupList() {
    return this.setupList;
  }

  public void setSetupList(List<SetupForCarSelection> setupList) {
    this.setupList = setupList;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupListForCarAndTrackResponse)) return false;
    final SetupListForCarAndTrackResponse other = (SetupListForCarAndTrackResponse) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$setupList = this.getSetupList();
    final Object other$setupList = other.getSetupList();
    if (this$setupList == null ? other$setupList != null : !this$setupList.equals(other$setupList)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupListForCarAndTrackResponse;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $setupList = this.getSetupList();
    result = result * PRIME + ($setupList == null ? 43 : $setupList.hashCode());
    return result;
  }

  public String toString() {
    return "SetupListForCarAndTrackResponse(setupList=" + this.getSetupList() + ")";
  }

  public static class SetupListForCarAndTrackResponseBuilder {
    private List<SetupForCarSelection> setupList;

    SetupListForCarAndTrackResponseBuilder() {
    }

    public SetupListForCarAndTrackResponseBuilder setupList(List<SetupForCarSelection> setupList) {
      this.setupList = setupList;
      return this;
    }

    public SetupListForCarAndTrackResponse build() {
      return new SetupListForCarAndTrackResponse(this.setupList);
    }

    public String toString() {
      return "SetupListForCarAndTrackResponse.SetupListForCarAndTrackResponseBuilder(setupList=" + this.setupList + ")";
    }
  }
}
