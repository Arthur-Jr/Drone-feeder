package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DroneRouteTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Testa a adição de um novo drone")
  void deveRetornarOIdDoDroneAdicionado() throws Exception {
    mockMvc.perform(post("/drone")).andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  @DisplayName("Testa a busca de drone por id existente")
  void deveRetornarDroneCorrespondenteAoId() throws Exception {
    mockMvc.perform(get("/drone/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.lat").value(0)).andExpect(jsonPath("$.lon").value(0))
        .andExpect(jsonPath("$.status").value("parado"));
  }

  @Test
  @DisplayName("Testa a busca de drone por id inexistente")
  void buscaPorIdInexistente() throws Exception {
    mockMvc.perform(get("/drone/5")).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.mensagem").value("Drone Inexistente"));
  }

  @Test
  @DisplayName("Testa a busca de todos drones")
  void buscaPorTodosDrone() throws Exception {
    mockMvc.perform(get("/drone")).andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1));
  }

  @Test
  @DisplayName("Testa a edição de drone")
  void editaDrone() throws Exception {
    Map<String, String> body = new HashMap<>();
    body.put("status", "entregando");

    mockMvc
        .perform(put("/drone").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(body)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("entregando"));
  }

  @Test
  @DisplayName("Testa a remoção de drone existente")
  void removeDrone() throws Exception {
    mockMvc.perform(delete("/drone/1")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("Testa a remoção de drone ineexistente")
  void removeDroneInexistente() throws Exception {
    mockMvc.perform(delete("/drone/1")).andExpect(status().isNotFound());
  }
}
