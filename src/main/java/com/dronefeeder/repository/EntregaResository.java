package com.dronefeeder.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Entrega;


public interface EntregaResository extends JpaRepository<Entrega, Long> {

  List<Entrega> findByDrone(Drone drone);
}
