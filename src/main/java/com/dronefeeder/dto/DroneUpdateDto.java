package com.dronefeeder.dto;

/**
 * DroneDto para update.
 */
public class DroneUpdateDto extends DroneDto {
  private int status;

  public DroneUpdateDto() {
    super();
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
