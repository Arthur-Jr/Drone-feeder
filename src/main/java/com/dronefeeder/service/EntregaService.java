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
    newDelivery.setDroneId(drone);

    if (drone.getStatus() == DroneStatusEnum.PARADO) {
      newDelivery.setStatus(EntregaStatusEnum.EV);
      drone.setStatus(DroneStatusEnum.ENTREGANDO);
      this.droneRepo.save(drone);
    } else {
      newDelivery.setStatus(EntregaStatusEnum.EP);
    }

    return repo.save(newDelivery);
  }

  public List<Entrega> getAllEntregaByDroneId(Long droneId) {
    Drone drone = this.droneRepo.getReferenceById(droneId);
    return this.repo.findByDroneId(drone);
  }

  private Drone getDroneToDelivery() {
    List<Drone> droneList = this.droneRepo.findAll();
    Drone avaibleDrone = droneList.stream().filter(x -> x.getStatus() == DroneStatusEnum.PARADO)
        .findFirst().orElse(null);

    if (avaibleDrone != null) {
      return avaibleDrone;
    }

    return droneList.get(0);
  }
}
