package com.dronefeeder.domain;

/**
 * Entrega enum status.
 */
public enum EntregaStatusEnum {
  EP("em espera"), EV("em viagem"), ET("entregue");

  private String value;

  private EntregaStatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
