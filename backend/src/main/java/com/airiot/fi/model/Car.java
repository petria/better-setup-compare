package com.airiot.fi.model;

import java.util.List;


public class Car {

  private long id;
  private String name;
  private List<Track> tracks;

  static long nextId = 1;


  public Car(String name, List<Track> tracks) {
    this.name = name;
    this.tracks = tracks;
    this.id = nextId++;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }

  public static long getNextId() {
    return nextId;
  }

  public static void setNextId(long nextId) {
    Car.nextId = nextId;
  }
}
