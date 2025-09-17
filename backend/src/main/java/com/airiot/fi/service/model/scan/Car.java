package com.airiot.fi.service.model.scan;

import java.util.HashMap;
import java.util.Map;

public class Car {

  private long id;
  private String carName;
  private String carFolderName;
  private long iniFileCount = 0;
  private Map<String, Track> tracksWithSetup = new HashMap<>();


  public Car() {
  }

  public long getId() {
    return this.id;
  }

  public String getCarName() {
    return this.carName;
  }

  public String getCarFolderName() {
    return this.carFolderName;
  }

  public long getIniFileCount() {
    return this.iniFileCount;
  }

  public Map<String, Track> getTracksWithSetup() {
    return this.tracksWithSetup;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setCarName(String carName) {
    this.carName = carName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public void setIniFileCount(long iniFileCount) {
    this.iniFileCount = iniFileCount;
  }

  public void setTracksWithSetup(Map<String, Track> tracksWithSetup) {
    this.tracksWithSetup = tracksWithSetup;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Car)) return false;
    final Car other = (Car) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    final Object this$carName = this.getCarName();
    final Object other$carName = other.getCarName();
    if (this$carName == null ? other$carName != null : !this$carName.equals(other$carName)) return false;
    final Object this$carFolderName = this.getCarFolderName();
    final Object other$carFolderName = other.getCarFolderName();
    if (this$carFolderName == null ? other$carFolderName != null : !this$carFolderName.equals(other$carFolderName))
      return false;
    if (this.getIniFileCount() != other.getIniFileCount()) return false;
    final Object this$tracksWithSetup = this.getTracksWithSetup();
    final Object other$tracksWithSetup = other.getTracksWithSetup();
    if (this$tracksWithSetup == null ? other$tracksWithSetup != null : !this$tracksWithSetup.equals(other$tracksWithSetup))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Car;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $id = this.getId();
    result = result * PRIME + (int) ($id >>> 32 ^ $id);
    final Object $carName = this.getCarName();
    result = result * PRIME + ($carName == null ? 43 : $carName.hashCode());
    final Object $carFolderName = this.getCarFolderName();
    result = result * PRIME + ($carFolderName == null ? 43 : $carFolderName.hashCode());
    final long $iniFileCount = this.getIniFileCount();
    result = result * PRIME + (int) ($iniFileCount >>> 32 ^ $iniFileCount);
    final Object $tracksWithSetup = this.getTracksWithSetup();
    result = result * PRIME + ($tracksWithSetup == null ? 43 : $tracksWithSetup.hashCode());
    return result;
  }

  public String toString() {
    return "Car(id=" + this.getId() + ", carName=" + this.getCarName() + ", carFolderName=" + this.getCarFolderName() + ", iniFileCount=" + this.getIniFileCount() + ", tracksWithSetup=" + this.getTracksWithSetup() + ")";
  }
}
