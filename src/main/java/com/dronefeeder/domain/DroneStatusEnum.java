package com.dronefeeder.domain;

public enum DroneStatusEnum {
  NONE(0), PARADO(1), ENTREGANDO(2);

  private int id;

  private DroneStatusEnum(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
