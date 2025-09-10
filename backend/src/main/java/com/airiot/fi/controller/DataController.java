package com.airiot.fi.controller;

import com.airiot.fi.model.Car;
import com.airiot.fi.model.Setup;
import com.airiot.fi.model.Track;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

  // Mock data for demonstration purposes
  private final List<Car> cars = Arrays.asList(
      new Car("Ferrari 488 GT3", Arrays.asList(
          new Track("Spa-Francorchamps", Arrays.asList(
              new Setup("Qualifying Setup"),
              new Setup("Race Setup")
          )),
          new Track("Monza", Collections.singletonList(
              new Setup("Low Downforce Setup")
          ))
      )),
      new Car("Porsche 911 GT3 R", Arrays.asList(
          new Track("Nurburgring", Arrays.asList(
              new Setup("Endurance Setup"),
              new Setup("Sprint Setup")
          )),
          new Track("Spa-Francorchamps", Collections.singletonList(
              new Setup("Aggressive Setup")
          ))
      ))
  );

  @GetMapping("/cars")
  public List<Car> getCars() {
    return cars;
  }

  @GetMapping("/cars/{carId}/tracks")
  public List<Track> getTracks(@PathVariable long carId) {
    System.out.println("getTracks called with carId: " + carId);
    return cars.stream()
        .filter(car -> car.getId() == carId)
        .findFirst()
        .map(Car::getTracks)
        .orElse(Collections.emptyList());
  }

  @GetMapping("/cars/{carId}/tracks/{trackId}/setups")
  public List<Setup> getSetups(@PathVariable long carId, @PathVariable long trackId) {
    return cars.stream()
        .filter(car -> car.getId() == carId)
        .findFirst()
        .flatMap(car -> car.getTracks().stream()
            .filter(track -> track.getId() == trackId)
            .findFirst()
            .map(Track::getSetups))
        .orElse(Collections.emptyList());
  }
}
