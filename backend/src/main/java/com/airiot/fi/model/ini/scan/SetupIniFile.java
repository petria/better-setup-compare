package com.airiot.fi.model.ini.scan;

public class SetupIniFile {

  private long id;

  private String carFolderName;

  private String trackFolderName;

  private String setupIniFileName;

  private String setupFullPath;


  public SetupIniFile() {
  }

  public long getId() {
    return this.id;
  }

  public String getCarFolderName() {
    return this.carFolderName;
  }

  public String getTrackFolderName() {
    return this.trackFolderName;
  }

  public String getSetupIniFileName() {
    return this.setupIniFileName;
  }

  public String getSetupFullPath() {
    return this.setupFullPath;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public void setTrackFolderName(String trackFolderName) {
    this.trackFolderName = trackFolderName;
  }

  public void setSetupIniFileName(String setupIniFileName) {
    this.setupIniFileName = setupIniFileName;
  }

  public void setSetupFullPath(String setupFullPath) {
    this.setupFullPath = setupFullPath;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupIniFile)) return false;
    final SetupIniFile other = (SetupIniFile) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$carFolderName = this.getCarFolderName();
    final Object other$carFolderName = other.getCarFolderName();
    if (this$carFolderName == null ? other$carFolderName != null : !this$carFolderName.equals(other$carFolderName))
      return false;
    final Object this$trackFolderName = this.getTrackFolderName();
    final Object other$trackFolderName = other.getTrackFolderName();
    if (this$trackFolderName == null ? other$trackFolderName != null : !this$trackFolderName.equals(other$trackFolderName))
      return false;
    final Object this$setupIniFileName = this.getSetupIniFileName();
    final Object other$setupIniFileName = other.getSetupIniFileName();
    if (this$setupIniFileName == null ? other$setupIniFileName != null : !this$setupIniFileName.equals(other$setupIniFileName))
      return false;
    final Object this$setupFullPath = this.getSetupFullPath();
    final Object other$setupFullPath = other.getSetupFullPath();
    if (this$setupFullPath == null ? other$setupFullPath != null : !this$setupFullPath.equals(other$setupFullPath))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupIniFile;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $id = this.getId();
    result = result * PRIME + (int) ($id >>> 32 ^ $id);
    final Object $carFolderName = this.getCarFolderName();
    result = result * PRIME + ($carFolderName == null ? 43 : $carFolderName.hashCode());
    final Object $trackFolderName = this.getTrackFolderName();
    result = result * PRIME + ($trackFolderName == null ? 43 : $trackFolderName.hashCode());
    final Object $setupIniFileName = this.getSetupIniFileName();
    result = result * PRIME + ($setupIniFileName == null ? 43 : $setupIniFileName.hashCode());
    final Object $setupFullPath = this.getSetupFullPath();
    result = result * PRIME + ($setupFullPath == null ? 43 : $setupFullPath.hashCode());
    return result;
  }

  public String toString() {
    return "SetupIniFile(id=" + this.getId() + ", carFolderName=" + this.getCarFolderName() + ", trackFolderName=" + this.getTrackFolderName() + ", setupIniFileName=" + this.getSetupIniFileName() + ", setupFullPath=" + this.getSetupFullPath() + ")";
  }
}
