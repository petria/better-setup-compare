package com.airiot.fi.service.model.carselector;


public class IniSections {

  private String name;

  private String selected;

  IniSections(String name, String selected) {
    this.name = name;
    this.selected = selected;
  }

  public static IniSectionsBuilder builder() {
    return new IniSectionsBuilder();
  }

  public String getName() {
    return this.name;
  }

  public String getSelected() {
    return this.selected;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSelected(String selected) {
    this.selected = selected;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof IniSections)) return false;
    final IniSections other = (IniSections) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    final Object this$selected = this.getSelected();
    final Object other$selected = other.getSelected();
    if (this$selected == null ? other$selected != null : !this$selected.equals(other$selected)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof IniSections;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $selected = this.getSelected();
    result = result * PRIME + ($selected == null ? 43 : $selected.hashCode());
    return result;
  }

  public String toString() {
    return "IniSections(name=" + this.getName() + ", selected=" + this.getSelected() + ")";
  }

  public static class IniSectionsBuilder {
    private String name;
    private String selected;

    IniSectionsBuilder() {
    }

    public IniSectionsBuilder name(String name) {
      this.name = name;
      return this;
    }

    public IniSectionsBuilder selected(String selected) {
      this.selected = selected;
      return this;
    }

    public IniSections build() {
      return new IniSections(this.name, this.selected);
    }

    public String toString() {
      return "IniSections.IniSectionsBuilder(name=" + this.name + ", selected=" + this.selected + ")";
    }
  }
}
