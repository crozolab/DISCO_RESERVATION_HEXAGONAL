package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ServicioCrearReserva {

    private static final String FUERA_DE_HORARIO = "Sobrepaso la hora de reserva";
    private static final String LUNES = "MONDAY";
    private static final String MARTES = "TUESDAY";
    private static final String MIERCOLES = "WEDNESDAY";
    private static final String SABADO = "SATURDAY";
    private static final int HORA_LIMITE = 15;

    private final Clock clock;
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(Clock clock, RepositorioReserva repositorioReserva) {
        this.clock = clock;
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarFecha(reserva);
        return this.repositorioReserva.crear(reserva);
    }

    private void validarFecha(Reserva reserva) {
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        String dia = localDateTime.getDayOfWeek().toString();
        int hora = localDateTime.getHour();
        boolean cumpleanos = descuentoCumpleanos(reserva);
        if (dia.equals(LUNES) || dia.equals(MARTES) || dia.equals(MIERCOLES)) {
            reserva.asignarPrecio(reserva.getCategoria(), true, cumpleanos);
        } else if (dia.equals(SABADO) && hora >= HORA_LIMITE) {
            throw new ExcepcionValorInvalido(FUERA_DE_HORARIO);
        } else {
            reserva.asignarPrecio(reserva.getCategoria(), false, cumpleanos);
        }
    }


    private boolean descuentoCumpleanos(Reserva reserva) {
        LocalDate fechaReserva = reserva.getFechaReserva();
        String fechaReservaFormateada = reserva.formateoFechas(fechaReserva);
        LocalDateTime fechaNacimiento = this.repositorioReserva.consultarFechaNacimiento(reserva.getIdUsuario());
        String fechaNacimientoFormateada = reserva.formateoFechas(fechaNacimiento.toLocalDate());
        boolean estaDeCumpleanos = fechaNacimientoFormateada.equals(fechaReservaFormateada);
        reserva.setObsequio(!estaDeCumpleanos);
        return estaDeCumpleanos;
    }


}
