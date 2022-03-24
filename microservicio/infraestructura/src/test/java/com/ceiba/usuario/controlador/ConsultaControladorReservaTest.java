package com.ceiba.usuario.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoUsuarioTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar reservas")
    void deberiaListarUsuarios() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/reserva")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nombre", is("test")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].categoria", is("vip")))
                .andExpect(jsonPath("$[0].fechaReserva", is("2022-11-14")))
                .andExpect(jsonPath("$[0].idUsuario", is(1)))
                .andExpect(jsonPath("$[0].precio", is(200000.0)));

    }

    @Test
    @DisplayName("Deberia listar reservas para determinado usuario ")
    void deberiaListarReservasUsuario () throws Exception {
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();
        mocMvc.perform(post("/reserva/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reserva))
                )
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("camilo")))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].categoria", is("vip")))
                .andExpect(jsonPath("$[0].fechaReserva", is("2022-11-14")))
                .andExpect(jsonPath("$[0].idUsuario", is(2)))
                .andExpect(jsonPath("$[0].precio", is(300000.0)))
                .andExpect(jsonPath("$[1].nombre", is("camilo")))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].categoria", is("general")))
                .andExpect(jsonPath("$[1].fechaReserva", is("2022-11-14")))
                .andExpect(jsonPath("$[1].idUsuario", is(2)))
                .andExpect(jsonPath("$[1].precio", is(100000.0)))
                .andExpect(status().isOk());

    }

}
