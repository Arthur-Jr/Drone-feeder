package com.dronefeeder.controller;

import com.dronefeeder.dto.DroneDto;
import com.dronefeeder.model.Drone;
import com.dronefeeder.service.DroneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<Drone> addDrone(@RequestBody DroneDto drone) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.addDrone(drone));
  }

  @GetMapping("/{id}")
  public Drone getDroneById(@PathVariable("id") Long id) {
    return this.service.getDroneById(id);
  }

  @GetMapping
  public List<Drone> getAllDrones() {
    return this.service.getAllDrones();
  }

}
