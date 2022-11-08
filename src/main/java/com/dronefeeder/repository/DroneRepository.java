package com.dronefeeder.repository;

import com.dronefeeder.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Drone Repo.
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

}
