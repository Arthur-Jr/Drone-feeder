package com.dronefeeder.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dronefeeder.domain.DroneStatusEnum;
import com.dronefeeder.domain.EntregaStatusEnum;
import com.dronefeeder.dto.DroneDto;
import com.dronefeeder.dto.DroneUpdateDto;
import com.dronefeeder.excepetion.DroneNotFountException;
import com.dronefeeder.model.Drone;
import com.dronefeeder.model.Entrega;
import com.dronefeeder.repository.DroneRepository;
import com.dronefeeder.repository.EntregaResository;

/**
 * Drone service.
 */
@Service
public class DroneService {

  @Autowired
  private DroneRepository repo;

  @Autowired
  private EntregaResository entregaRepo;

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

  /**
   * Acessa um drone pelo id.
   */
  public Drone getDroneById(Long id) {
    if (!this.repo.existsById(id)) {
      throw new DroneNotFountException();
    }
    Drone drone = this.repo.getReferenceById(id);

    this.checkDroneCurrentDelivery(drone);

    return drone;
  }


  /**
   * Lista todos drones.
   */
  public List<Drone> getAllDrones() {
    return this.repo.findAll();
  }

  /**
   * Remove um drone pelo id.
   */
  public void deleteDrone(Long id) {
    if (!this.repo.existsById(id)) {
      throw new DroneNotFountException();
    }

    this.repo.deleteById(id);
  }

  /**
   * Drone edit.
   */
  public Drone editDrone(Long id, DroneUpdateDto dronePayload) {
    Drone droneToUpdate = this.getDroneById(id);
    if (dronePayload.getLat() != 0.0) {
      droneToUpdate.setLat(dronePayload.getLat());
    }

    if (dronePayload.getLon() != 0.0) {
      droneToUpdate.setLon(dronePayload.getLon());
    }

    if (dronePayload.getStatus() != 0) {
      droneToUpdate.setStatus(DroneStatusEnum.valueOf(dronePayload.getStatus()));
    }

    return this.repo.save(droneToUpdate);
  }

  private void checkDroneCurrentDelivery(Drone drone) {
    LocalDateTime date = LocalDateTime.now().withNano(0);
    List<Entrega> entregas = this.entregaRepo.findByDrone(drone);
    Entrega currentEntrega = entregas.stream()
        .filter(x -> x.getStatus() == EntregaStatusEnum.EM_VIAGEM).findFirst().orElse(null);

    if (currentEntrega.getDeliveryDate().isBefore(date)) {
      currentEntrega.setStatus(EntregaStatusEnum.ENTREGUE);
      this.entregaRepo.save(currentEntrega);
      this.updateCurrentDroneDelivery(entregas, drone);
    }
  }

  private void updateCurrentDroneDelivery(List<Entrega> entregas, Drone drone) {
    LocalDateTime date = LocalDateTime.now().withNano(0);
    Entrega nextEntrega = entregas.stream()
        .filter(x -> x.getStatus() == EntregaStatusEnum.EM_ESPERA).findFirst().orElse(null);

    if (nextEntrega != null) {
      nextEntrega.setDeliveryDate(date.plusMinutes(10));
      nextEntrega.setDeliveryStartTime(date);
      nextEntrega.setStatus(EntregaStatusEnum.EM_VIAGEM);
      this.entregaRepo.save(nextEntrega);
    } else {
      drone.setStatus(DroneStatusEnum.PARADO);
      this.repo.save(drone);
    }
  }
}
