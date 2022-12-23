package com.dronefeeder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dronefeeder.excepetion.DataError;
import com.dronefeeder.excepetion.DroneNotFountException;
import com.dronefeeder.excepetion.EntregaNotFound;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

/**
 * Advice manager.
 */
@ControllerAdvice
public class AdviceManager {

  /**
   * lon lat format error handler.
   */
  @ExceptionHandler(MysqlDataTruncation.class)
  public ResponseEntity<DataError> handleLatLonInvalidFormat(Exception e) {
    DataError errorResponse = new DataError("lon ou lat inválido");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * Not found drone error handler.
   */
  @ExceptionHandler({DroneNotFountException.class, EntregaNotFound.class})
  public ResponseEntity<DataError> handleNotFoundDrone(Exception e) {
    DataError errorResponse = new DataError(e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /**
   * Internal server erro handler.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<DataError> handleGenericError(Exception e) {
    DataError errorResponse = new DataError("Internal server error");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
