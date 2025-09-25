package com.airiot.fi.model.ini.carselector;


import java.util.List;

public class CompareSetupsRequest {

  private List<IniSections> iniSections;

  public CompareSetupsRequest(List<IniSections> iniSections) {
    this.iniSections = iniSections;
  }

  public CompareSetupsRequest() {
  }

  public static CompareSetupsRequestBuilder builder() {
    return new CompareSetupsRequestBuilder();
  }

  public List<IniSections> getIniSections() {
    return this.iniSections;
  }

  public void setIniSections(List<IniSections> iniSections) {
    this.iniSections = iniSections;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof CompareSetupsRequest)) return false;
    final CompareSetupsRequest other = (CompareSetupsRequest) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$iniSections = this.getIniSections();
    final Object other$iniSections = other.getIniSections();
    if (this$iniSections == null ? other$iniSections != null : !this$iniSections.equals(other$iniSections))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof CompareSetupsRequest;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $iniSections = this.getIniSections();
    result = result * PRIME + ($iniSections == null ? 43 : $iniSections.hashCode());
    return result;
  }

  public String toString() {
    return "CompareSetupsRequest(iniSections=" + this.getIniSections() + ")";
  }

  public static class CompareSetupsRequestBuilder {
    private List<IniSections> iniSections;

    CompareSetupsRequestBuilder() {
    }

    public CompareSetupsRequestBuilder iniSections(List<IniSections> iniSections) {
      this.iniSections = iniSections;
      return this;
    }

    public CompareSetupsRequest build() {
      return new CompareSetupsRequest(this.iniSections);
    }

    public String toString() {
      return "CompareSetupsRequest.CompareSetupsRequestBuilder(iniSections=" + this.iniSections + ")";
    }
  }
}
