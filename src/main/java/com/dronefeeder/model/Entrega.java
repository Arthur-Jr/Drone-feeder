package com.dronefeeder.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.dronefeeder.domain.EntregaStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entrega Entity.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entrega {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private EntregaStatusEnum status;

  private LocalDateTime created;

  private LocalDateTime deliveryDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "droneId")
  private Drone droneId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EntregaStatusEnum getStatus() {
    return status;
  }

  public void setStatus(EntregaStatusEnum status) {
    this.status = status;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Drone getDroneId() {
    return droneId;
  }

  public void setDroneId(Drone droneId) {
    this.droneId = droneId;
  }
}
