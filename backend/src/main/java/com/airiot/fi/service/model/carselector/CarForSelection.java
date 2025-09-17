package com.airiot.fi.service.model.carselector;

public class CarForSelection {

  private long id;
  private long carTracksWithSetup;
  private String carName;
  private String carFolderName;

  CarForSelection(long id, long carTracksWithSetup, String carName, String carFolderName) {
    this.id = id;
    this.carTracksWithSetup = carTracksWithSetup;
    this.carName = carName;
    this.carFolderName = carFolderName;
  }

  public static CarForSelectionBuilder builder() {
    return new CarForSelectionBuilder();
  }


  public long getId() {
    return this.id;
  }

  public long getCarTracksWithSetup() {
    return this.carTracksWithSetup;
  }

  public String getCarName() {
    return this.carName;
  }

  public String getCarFolderName() {
    return this.carFolderName;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setCarTracksWithSetup(long carTracksWithSetup) {
    this.carTracksWithSetup = carTracksWithSetup;
  }

  public void setCarName(String carName) {
    this.carName = carName;
  }

  public void setCarFolderName(String carFolderName) {
    this.carFolderName = carFolderName;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof CarForSelection)) return false;
    final CarForSelection other = (CarForSelection) o;
    if (!other.canEqual((Object) this)) return false;
    if (this.getId() != other.getId()) return false;
    if (this.getCarTracksWithSetup() != other.getCarTracksWithSetup()) return false;
    final Object this$carName = this.getCarName();
    final Object other$carName = other.getCarName();
    if (this$carName == null ? other$carName != null : !this$carName.equals(other$carName)) return false;
    final Object this$carFolderName = this.getCarFolderName();
    final Object other$carFolderName = other.getCarFolderName();
    if (this$carFolderName == null ? other$carFolderName != null : !this$carFolderName.equals(other$carFolderName))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof CarForSelection;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long $id = this.getId();
    result = result * PRIME + (int) ($id >>> 32 ^ $id);
    final long $carTracksWithSetup = this.getCarTracksWithSetup();
    result = result * PRIME + (int) ($carTracksWithSetup >>> 32 ^ $carTracksWithSetup);
    final Object $carName = this.getCarName();
    result = result * PRIME + ($carName == null ? 43 : $carName.hashCode());
    final Object $carFolderName = this.getCarFolderName();
    result = result * PRIME + ($carFolderName == null ? 43 : $carFolderName.hashCode());
    return result;
  }

  public String toString() {
    return "CarForSelection(id=" + this.getId() + ", carTracksWithSetup=" + this.getCarTracksWithSetup() + ", carName=" + this.getCarName() + ", carFolderName=" + this.getCarFolderName() + ")";
  }

  public static class CarForSelectionBuilder {
    private long id;
    private long carTracksWithSetup;
    private String carName;
    private String carFolderName;

    CarForSelectionBuilder() {
    }

    public CarForSelectionBuilder id(long id) {
      this.id = id;
      return this;
    }

    public CarForSelectionBuilder carTracksWithSetup(long carTracksWithSetup) {
      this.carTracksWithSetup = carTracksWithSetup;
      return this;
    }

    public CarForSelectionBuilder carName(String carName) {
      this.carName = carName;
      return this;
    }

    public CarForSelectionBuilder carFolderName(String carFolderName) {
      this.carFolderName = carFolderName;
      return this;
    }

    public CarForSelection build() {
      return new CarForSelection(this.id, this.carTracksWithSetup, this.carName, this.carFolderName);
    }

    public String toString() {
      return "CarForSelection.CarForSelectionBuilder(id=" + this.id + ", carTracksWithSetup=" + this.carTracksWithSetup + ", carName=" + this.carName + ", carFolderName=" + this.carFolderName + ")";
    }
  }
}
