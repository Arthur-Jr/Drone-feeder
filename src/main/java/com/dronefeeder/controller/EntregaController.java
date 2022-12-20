package com.dronefeeder.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dronefeeder.dto.EntregaDto;
import com.dronefeeder.model.Entrega;
import com.dronefeeder.service.EntregaService;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
  @Autowired
  private EntregaService service;

  @PostMapping
  public ResponseEntity<Entrega> addEntrega(@RequestBody EntregaDto entrega) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.addEntrega(entrega));
  }

  @GetMapping("/{id}")
  public List<Entrega> getEntregaByDroneId(@PathVariable("id") Long id) {
    return this.service.getAllEntregaByDroneId(id);
  }
}
