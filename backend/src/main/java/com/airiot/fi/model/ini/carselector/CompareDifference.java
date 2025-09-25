package com.airiot.fi.model.ini.carselector;

import java.util.List;

public class CompareDifference {

  private List<String> differences;

  CompareDifference(List<String> differences) {
    this.differences = differences;
  }

  public static CompareDifferenceBuilder builder() {
    return new CompareDifferenceBuilder();
  }

  public List<String> getDifferences() {
    return this.differences;
  }

  public void setDifferences(List<String> differences) {
    this.differences = differences;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof CompareDifference)) return false;
    final CompareDifference other = (CompareDifference) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$differences = this.getDifferences();
    final Object other$differences = other.getDifferences();
    if (this$differences == null ? other$differences != null : !this$differences.equals(other$differences))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof CompareDifference;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $differences = this.getDifferences();
    result = result * PRIME + ($differences == null ? 43 : $differences.hashCode());
    return result;
  }

  public String toString() {
    return "CompareDifference(differences=" + this.getDifferences() + ")";
  }

  public static class CompareDifferenceBuilder {
    private List<String> differences;

    CompareDifferenceBuilder() {
    }

    public CompareDifferenceBuilder differences(List<String> differences) {
      this.differences = differences;
      return this;
    }

    public CompareDifference build() {
      return new CompareDifference(this.differences);
    }

    public String toString() {
      return "CompareDifference.CompareDifferenceBuilder(differences=" + this.differences + ")";
    }
  }
}
