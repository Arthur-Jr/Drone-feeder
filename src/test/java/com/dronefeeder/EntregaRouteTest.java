package com.dronefeeder;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.dronefeeder.dto.EntregaDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntregaRouteTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @DisplayName("Testa a adição de uma nova entrega")
  void deveRetornarIdDaEntregaAdicionada() throws Exception {
    DroneDto body = new DroneDto();
    mockMvc.perform(post("/drone").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(body)));

    EntregaDto entrega = new EntregaDto();
    entrega.setAddress("casa");

    mockMvc
        .perform(post("/entrega").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(entrega)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
  }

  @Test
  @Order(2)
  @DisplayName("Testa se um drone foi escolhido pra entrega")
  void checaSeUmDroneFoiEscolhidoParaEntrega() throws Exception {
    mockMvc.perform(get("/entrega/1")).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.status").value("EM_VIAGEM"));

    mockMvc.perform(get("/drone/1")).andExpect(jsonPath("$.status").value("ENTREGANDO"));
  }

  @Test
  @Order(3)
  @DisplayName("Testa se não tiver drone disponivel a entrga fica em espera")
  void TestaEntregaEmEspera() throws Exception {
    EntregaDto entrega = new EntregaDto();
    entrega.setAddress("casa");

    mockMvc
        .perform(post("/entrega").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(entrega)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(2))
        .andExpect(jsonPath("$.status").value("EM_ESPERA"));

    mockMvc.perform(get("/entrega/list/1")).andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  @Order(4)
  @DisplayName("Testa se tiver drone disponivel a viagem começa")
  void TestaEntregaParaUmNovoDrone() throws Exception {
    DroneDto body = new DroneDto();
    mockMvc.perform(post("/drone").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(body)));

    EntregaDto entrega = new EntregaDto();
    entrega.setAddress("casa");

    mockMvc
        .perform(post("/entrega").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(entrega)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(3))
        .andExpect(jsonPath("$.status").value("EM_VIAGEM"));

    mockMvc.perform(get("/entrega/list/2")).andExpect(jsonPath("$", hasSize(1)));
  }
}
