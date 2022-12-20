package com.dronefeeder.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dronefeeder.domain.DroneStatusEnum;
import com.dronefeeder.domain.EntregaStatusEnum;
import com.dronefeeder.dto.EntregaDto;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Entrega;
import com.dronefeeder.repository.DroneRepository;
import com.dronefeeder.repository.EntregaResository;

@Service
public class EntregaService {
  @Autowired
  private EntregaResository repo;

  @Autowired
  private DroneRepository droneRepo;

  public Entrega addEntrega(EntregaDto payload) {
    Entrega newDelivery = new Entrega();
    LocalDateTime date = LocalDateTime.now().withNano(0);
    Drone drone = this.getDroneToDelivery();

    newDelivery.setAddress(payload.getAddress());
    newDelivery.setCreated(date);
    newDelivery.setDeliveryDate(date.plusMinutes(10));
    newDelivery.setDrone(drone);

    if (drone.getStatus() == DroneStatusEnum.PARADO) {
      newDelivery.setStatus(EntregaStatusEnum.EM_VIAGEM);
      drone.setStatus(DroneStatusEnum.ENTREGANDO);
      this.droneRepo.save(drone);
    } else {
      newDelivery.setStatus(EntregaStatusEnum.EM_ESPERA);
    }

    return repo.save(newDelivery);
  }

  public List<Entrega> getAllEntregaByDroneId(Long droneId) {
    Drone drone = this.droneRepo.getReferenceById(droneId);
    return this.repo.findByDrone(drone);
  }

  private Drone getDroneToDelivery() {
    List<Drone> droneList = this.droneRepo.findAll();
    Drone avaibleDrone = droneList.stream().filter(x -> x.getStatus() == DroneStatusEnum.PARADO)
        .findFirst().orElse(null);

    if (avaibleDrone != null) {
      return avaibleDrone;
    }

    Drone droneWithLessEntrega = droneList.get(0);
    int count = this.getAllEntregaByDroneId(droneList.get(0).getId()).size();
    for (Drone d : droneList) {
      List<Entrega> entregas = this.getAllEntregaByDroneId(d.getId());

      if (entregas.size() < count) {
        count = entregas.size();
        droneWithLessEntrega = d;
      }
    }

    return droneWithLessEntrega;
  }
}
