package com.dronefeeder.model;

import com.dronefeeder.domain.DroneStatusEnum;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Drone Entity.
 */
@Entity
public class Drone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private DroneStatusEnum status;

  private LocalDateTime lastConnection;

  private LocalDateTime created;

  private double lon;

  private double lat;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DroneStatusEnum getStatus() {
    return status;
  }

  public void setStatus(DroneStatusEnum status) {
    this.status = status;
  }

  public LocalDateTime getLastConnection() {
    return lastConnection;
  }

  public void setLastConnection(LocalDateTime lastConnection) {
    this.lastConnection = lastConnection;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}
