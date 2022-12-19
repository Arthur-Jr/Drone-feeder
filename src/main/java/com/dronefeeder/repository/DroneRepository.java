package com.dronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dronefeeder.model.Drone;

/**
 * Drone Repo.
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

}
