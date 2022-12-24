package com.dronefeeder.excepetion;

public class EntregaNotFound extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public EntregaNotFound() {
    super("Entrega Inexistente");
  }
}
