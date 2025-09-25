package com.airiot.fi.model.ini.scan;

public class SetupIniFileScanStats {

  private boolean scanDone = false;

  private String scanDir;

  private String configKeyMapFile;

  private int uniqueSetupFiles;
  private int carDirs;
  private int trackDirs;
  private long scanTime;

  public SetupIniFileScanStats() {
  }

  public boolean isScanDone() {
    return scanDone;
  }

  public void setScanDone(boolean scanDone) {
    this.scanDone = scanDone;
  }

  public String getScanDir() {
    return this.scanDir;
  }

  public String getConfigKeyMapFile() {
    return this.configKeyMapFile;
  }

  public int getUniqueSetupFiles() {
    return this.uniqueSetupFiles;
  }

  public int getCarDirs() {
    return this.carDirs;
  }

  public int getTrackDirs() {
    return this.trackDirs;
  }

  public long getScanTime() {
    return this.scanTime;
  }

  public void setScanDir(String scanDir) {
    this.scanDir = scanDir;
  }

  public void setConfigKeyMapFile(String configKeyMapFile) {
    this.configKeyMapFile = configKeyMapFile;
  }

  public void setUniqueSetupFiles(int uniqueSetupFiles) {
    this.uniqueSetupFiles = uniqueSetupFiles;
  }

  public void setCarDirs(int carDirs) {
    this.carDirs = carDirs;
  }

  public void setTrackDirs(int trackDirs) {
    this.trackDirs = trackDirs;
  }

  public void setScanTime(long scanTime) {
    this.scanTime = scanTime;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof SetupIniFileScanStats)) return false;
    final SetupIniFileScanStats other = (SetupIniFileScanStats) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$scanDir = this.getScanDir();
    final Object other$scanDir = other.getScanDir();
    if (this$scanDir == null ? other$scanDir != null : !this$scanDir.equals(other$scanDir)) return false;
    final Object this$configKeyMapFile = this.getConfigKeyMapFile();
    final Object other$configKeyMapFile = other.getConfigKeyMapFile();
    if (this$configKeyMapFile == null ? other$configKeyMapFile != null : !this$configKeyMapFile.equals(other$configKeyMapFile))
      return false;
    if (this.getUniqueSetupFiles() != other.getUniqueSetupFiles()) return false;
    if (this.getCarDirs() != other.getCarDirs()) return false;
    if (this.getTrackDirs() != other.getTrackDirs()) return false;
    if (this.getScanTime() != other.getScanTime()) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SetupIniFileScanStats;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $scanDir = this.getScanDir();
    result = result * PRIME + ($scanDir == null ? 43 : $scanDir.hashCode());
    final Object $configKeyMapFile = this.getConfigKeyMapFile();
    result = result * PRIME + ($configKeyMapFile == null ? 43 : $configKeyMapFile.hashCode());
    result = result * PRIME + this.getUniqueSetupFiles();
    result = result * PRIME + this.getCarDirs();
    result = result * PRIME + this.getTrackDirs();
    final long $scanTime = this.getScanTime();
    result = result * PRIME + (int) ($scanTime >>> 32 ^ $scanTime);
    return result;
  }

  public String toString() {
    return "SetupIniFileScanStats(scanDir=" + this.getScanDir() + ", configKeyMapFile=" + this.getConfigKeyMapFile() + ", uniqueSetupFiles=" + this.getUniqueSetupFiles() + ", carDirs=" + this.getCarDirs() + ", trackDirs=" + this.getTrackDirs() + ", scanTime=" + this.getScanTime() + ")";
  }
}
