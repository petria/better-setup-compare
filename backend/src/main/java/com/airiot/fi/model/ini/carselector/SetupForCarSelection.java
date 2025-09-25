package com.airiot.fi.model.ini.carselector;

public class SetupForCarSelection {

  private long id;

  private String setupIniFileName;

  SetupForCarSelection(long id, String setupIniFileName) {
    this.id = id;
    this.setupIniFileName = setupIniFileName;
  }

  public static SetupForCarSelectionBuilder builder() {
    return new SetupForCarSelectionBuilder();
  }

  public long getId() {
    return this.id;
  }

  public String getSetupIniFileName() {
    return this.setupIniFileName;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setSetupIniFileName(String setupIniFileName) {
    this.setupIniFileName = setupIniFileName;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupForCarSelection)) return false;
    final SetupForCarSelection other = (SetupForCarSelection) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$setupIniFileName = this.getSetupIniFileName();
    final Object other$setupIniFileName = other.getSetupIniFileName();
    if (this$setupIniFileName == null ? other$setupIniFileName != null : !this$setupIniFileName.equals(other$setupIniFileName))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupForCarSelection;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $id = this.getId();
    result = result * PRIME + (int) ($id >>> 32 ^ $id);
    final Object $setupIniFileName = this.getSetupIniFileName();
    result = result * PRIME + ($setupIniFileName == null ? 43 : $setupIniFileName.hashCode());
    return result;
  }

  public String toString() {
    return "SetupForCarSelection(id=" + this.getId() + ", setupIniFileName=" + this.getSetupIniFileName() + ")";
  }

  public static class SetupForCarSelectionBuilder {
    private long id;
    private String setupIniFileName;

    SetupForCarSelectionBuilder() {
    }

    public SetupForCarSelectionBuilder id(long id) {
      this.id = id;
      return this;
    }

    public SetupForCarSelectionBuilder setupIniFileName(String setupIniFileName) {
      this.setupIniFileName = setupIniFileName;
      return this;
    }

    public SetupForCarSelection build() {
      return new SetupForCarSelection(this.id, this.setupIniFileName);
    }

    public String toString() {
      return "SetupForCarSelection.SetupForCarSelectionBuilder(id=" + this.id + ", setupIniFileName=" + this.setupIniFileName + ")";
    }
  }
}
