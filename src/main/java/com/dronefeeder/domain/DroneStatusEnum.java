package com.dronefeeder.domain;

/**
 * Drone enum status.
 */
public enum DroneStatusEnum {
  NONE(0), PARADO(1), ENTREGANDO(2);

  private int id;

  private DroneStatusEnum(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  /**
   * Get value by index.
   */
  public static DroneStatusEnum valueOf(int index) {
    try {
      return (DroneStatusEnum) DroneStatusEnum.values()[index];
    } catch (ArrayIndexOutOfBoundsException e) {
      return DroneStatusEnum.values()[0];
    }
  }
}
