package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.ServicioCrearReserva;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ServicioCrearReservaTest {



    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la categoria de la reserva")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaCategoriaDeLaReserva() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("platino").build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(Clock.systemDefaultZone(), repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorInvalido.class,"la categoria seleccionada no existe");
    }


    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la fecha se dia sabado pasadas 3 pm")
    void deberiaLanzarUnaExcepcionCuandoSeValideSabado() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-19T19:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-11-06T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("vip").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionValorInvalido.class,"Sobrepaso la hora de reserva");
    }

    @Test
    @DisplayName("Deberia ser precio normal el dia sabado antes 3 pm")
    void deberiaSerPrecioNormalCuandoSeValideSabado() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-19T08:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-11-07T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("vip").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(2L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(2L,idReserva);
        assertEquals(200000.0,reserva.getPrecio());
    }

    @Test
    @DisplayName("Deberia dar descuento y descuento adicional por cumpleaños")
    void deberiaDescuentoFull() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-14T19:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-11-06T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("vip").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(2L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act
       Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(2L,idReserva);
        assertEquals(120000.0,reserva.getPrecio());
    }


    @Test
    @DisplayName("Deberia dar SOLO descuento por cumpleaños ")
    void deberiaDescuentoSoloCumpleaños() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-18T19:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-11-06T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("vip").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(2L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(2L,idReserva);
        assertEquals(190000.0,reserva.getPrecio());
    }


    @Test
    @DisplayName("Deberia dar SOLO descuento por FECHA VIP ")
    void deberiaDescuentoSoloFecha() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-14T19:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-05-07T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("vip").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(2L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(2L,idReserva);
        assertEquals(130000.0,reserva.getPrecio());
    }


    @Test
    @DisplayName("Deberia dar SOLO descuento por FECHA VIP ")
    void deberiaDescuentoSoloFechaGeneral() {
        // arrange
        Clock fechaActual = Clock.fixed(Instant.parse("2022-03-14T19:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime fechaCumpleaños = LocalDateTime.parse("1992-05-07T09:57:45.106065100");
        LocalDate fechaDelEvento = LocalDate.parse("2022-11-06");
        Reserva reserva = new ReservaTestDataBuilder().conCategoria("general").conFechaReserva(fechaDelEvento).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        when(repositorioReserva.consultarFechaNacimiento(Mockito.anyInt())).thenReturn(fechaCumpleaños);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(2L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(fechaActual, repositorioReserva);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(2L,idReserva);
        assertEquals(52000.0,reserva.getPrecio());
    }


}
