package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.dronefeeder.dto.DroneDto;
import com.dronefeeder.dto.DroneUpdateDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testes da rota de drone.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DroneRouteTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @DisplayName("Testa a adição de um novo drone")
  void deveRetornarIdDoDroneAdicionado() throws Exception {
    DroneDto body = new DroneDto();

    mockMvc
        .perform(post("/drone").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(3));
  }

  @Test
  @Order(2)
  @DisplayName("Testa a busca de drone por id existente")
  void deveRetornarDroneCorrespondenteAoId() throws Exception {
    mockMvc.perform(get("/drone/3")).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(3));
  }

  @Test
  @Order(3)
  @DisplayName("Testa a busca de drone por id inexistente")
  void buscaPorIdInexistente() throws Exception {
    mockMvc.perform(get("/drone/5")).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Drone Inexistente"));
  }

  @Test
  @Order(4)
  @DisplayName("Testa a busca de todos drones")
  void buscaPorTodosDrone() throws Exception {
    mockMvc.perform(get("/drone")).andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(3));
  }

  @Test
  @Order(5)
  @DisplayName("Testa a edição de drone")
  void editaDrone() throws Exception {
    DroneUpdateDto body = new DroneUpdateDto();
    body.setStatus(2);

    mockMvc
        .perform(put("/drone/3").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ENTREGANDO"));
  }

  @Test
  @Order(6)
  @DisplayName("Testa a remoção de drone existente")
  void removeDrone() throws Exception {
    mockMvc.perform(delete("/drone/3")).andExpect(status().isOk());
  }

  @Test
  @Order(7)
  @DisplayName("Testa a remoção de drone ineexistente")
  void removeDroneInexistente() throws Exception {
    mockMvc.perform(delete("/drone/3")).andExpect(status().isNotFound());
  }
}
