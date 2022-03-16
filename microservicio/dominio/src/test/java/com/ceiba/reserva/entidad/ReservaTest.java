package com.ceiba.reserva.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservaTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteLaReserva() {
        // arrange
        LocalDate fechaReserva = LocalDate.now();
        //act
        Reserva reserva = new ReservaTestDataBuilder().conFechaReserva(fechaReserva).conId(1L).conObsequio(true).build();
        reserva.setFechaReserva(fechaReserva);
        //assert
        assertEquals(1, reserva.getId());
        assertEquals("1234", reserva.getNombre());
        assertEquals("vip", reserva.getCategoria());
        assertEquals(1, reserva.getIdUsuario());
        assertEquals(80000, reserva.getPrecio());
        assertEquals(fechaReserva, reserva.getFechaReserva());
        assertEquals(true, reserva.isObsequio());
    }

    @Test
    void deberiaFallarSinNombreDeReserva() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de usuario");
    }

    @Test
    void deberiaFallarSinCategoria() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conCategoria(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la categoria de la reserva");
    }

    @Test
    void deberiaFallarSinFechaReserva() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaReserva(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de la reserva");
    }



}
