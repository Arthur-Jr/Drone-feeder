package com.dronefeeder.service;

import com.dronefeeder.domain.DroneStatusEnum;
import com.dronefeeder.dto.DroneDto;
import com.dronefeeder.model.Drone;
import com.dronefeeder.repository.DroneRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Drone service.
 */
@Service
public class DroneService {

  @Autowired
  private DroneRepository repo;

  /**
   * Adiciona drone no DB.
   */
  public Drone addDrone(DroneDto dronePayload) {
    Drone newDrone = new Drone();
    LocalDateTime date = LocalDateTime.now().withNano(0);

    newDrone.setLat(dronePayload.getLat());
    newDrone.setLon(dronePayload.getLon());
    newDrone.setCreated(date);
    newDrone.setLastConnection(date);
    newDrone.setStatus(DroneStatusEnum.PARADO);

    return this.repo.save(newDrone);

  }
}
