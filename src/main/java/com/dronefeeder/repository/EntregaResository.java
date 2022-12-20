package com.dronefeeder.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Entrega;

@Repository
public interface EntregaResository extends JpaRepository<Entrega, Long> {

  List<Entrega> findByDroneId(Drone droneId);
}
