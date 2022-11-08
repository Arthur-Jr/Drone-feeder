package com.dronefeeder.controller;

import com.dronefeeder.excepetion.DataError;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
