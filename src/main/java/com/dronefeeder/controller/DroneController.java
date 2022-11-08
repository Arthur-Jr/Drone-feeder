package com.dronefeeder.controller;

import com.dronefeeder.dto.DroneDto;
import com.dronefeeder.model.Drone;
import com.dronefeeder.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Drone controller.
 */
@RestController
@RequestMapping("/drone")
public class DroneController {

  @Autowired
  private DroneService service;

  @PostMapping
  public Drone addDrone(@RequestBody DroneDto drone) {
    return this.service.addDrone(drone);
  }

}
