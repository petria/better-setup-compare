package com.airiot.fi.model.ini.carselector;


import java.util.List;

public class CompareSetupsResponse {

  private List<CompareDifference> differences;

  CompareSetupsResponse(List<CompareDifference> differences) {
    this.differences = differences;
  }

  public static CompareSetupsResponseBuilder builder() {
    return new CompareSetupsResponseBuilder();
  }

  public List<CompareDifference> getDifferences() {
    return this.differences;
  }

  public void setDifferences(List<CompareDifference> differences) {
    this.differences = differences;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof CompareSetupsResponse)) return false;
    final CompareSetupsResponse other = (CompareSetupsResponse) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$differences = this.getDifferences();
    final Object other$differences = other.getDifferences();
    if (this$differences == null ? other$differences != null : !this$differences.equals(other$differences))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof CompareSetupsResponse;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $differences = this.getDifferences();
    result = result * PRIME + ($differences == null ? 43 : $differences.hashCode());
    return result;
  }

  public String toString() {
    return "CompareSetupsResponse(differences=" + this.getDifferences() + ")";
  }

  public static class CompareSetupsResponseBuilder {
    private List<CompareDifference> differences;

    CompareSetupsResponseBuilder() {
    }

    public CompareSetupsResponseBuilder differences(List<CompareDifference> differences) {
      this.differences = differences;
      return this;
    }

    public CompareSetupsResponse build() {
      return new CompareSetupsResponse(this.differences);
    }

    public String toString() {
      return "CompareSetupsResponse.CompareSetupsResponseBuilder(differences=" + this.differences + ")";
    }
  }
}
