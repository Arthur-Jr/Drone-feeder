package com.dronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dronefeeder.model.Drone;


/**
 * Drone Repo.
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {

}
