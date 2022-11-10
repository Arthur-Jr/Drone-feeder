package com.dronefeeder.excepetion;

/**
 * Drone not found exception.
 */
public class DroneNotFountException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DroneNotFountException() {
    super("Drone Inexistente");
  }

}
