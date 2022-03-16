package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ServicioCrearReserva {

    private static final String FUERA_DE_HORARIO = "Sobrepaso la hora de reserva";
    private static final String CATEGORIA_GENERAL = "general";
    private static final String CATEGORIA_VIP = "vip";
    private static final String LUNES = "MONDAY";
    private static final String MARTES = "TUESDAY";
    private static final String MIERCOLES = "WEDNESDAY";
    private static final String SABADO = "SATURDAY";
    private static final String CATEGORIA_ERRONEA = "la categoria seleccionada no existe";
    private static final float PRECIO_GENERAL = 80000;
    private static final float PRECIO_VIP = 200000;
    private static final int HORA_LIMITE = 15;
    private static final double DESCUENTO_DIAS = 0.35;
    private static final double DESCUENTO_CUMPLEANOS = 0.05;

    private final Clock clock;
    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(Clock clock, RepositorioReserva repositorioReserva) {
        this.clock = clock;
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarCategoria(reserva);
        validarFecha(reserva);
        return this.repositorioReserva.crear(reserva);
    }


    private void validarCategoria(Reserva reserva) {
        String categoria = reserva.getCategoria();
        boolean categoriaValida = categoria.equalsIgnoreCase(CATEGORIA_GENERAL) || categoria.equalsIgnoreCase(CATEGORIA_VIP);
        if (!categoriaValida) {
            throw new ExcepcionValorInvalido(CATEGORIA_ERRONEA);
        }
    }

    private void validarFecha(Reserva reserva) {
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        String dia = localDateTime.getDayOfWeek().toString();
        int hora = localDateTime.getHour();
        boolean cumpleanos = descuentoCumpleanos(reserva);
        if (dia.equals(LUNES) || dia.equals(MARTES) || dia.equals(MIERCOLES)) {
            asignarPrecio(reserva, true, cumpleanos);
        } else if (dia.equals(SABADO) && hora >= HORA_LIMITE) {
            throw new ExcepcionValorInvalido(FUERA_DE_HORARIO);
        } else {
            asignarPrecio(reserva, false, cumpleanos);
        }
    }

    private void asignarPrecio(Reserva reserva, boolean descuento, boolean cumpleanos) {
        String categoria = reserva.getCategoria();
        float precio = (categoria.equalsIgnoreCase(CATEGORIA_VIP)) ? PRECIO_VIP : PRECIO_GENERAL;
        if (descuento && cumpleanos) {
            precio = (float) (precio * (1 - (DESCUENTO_DIAS + DESCUENTO_CUMPLEANOS)));
        } else if (descuento) {
            precio = (float) (precio * (1 - DESCUENTO_DIAS));
        } else if (cumpleanos) {
            precio = (float) (precio * (1 - DESCUENTO_CUMPLEANOS));
        }
        reserva.setPrecio(precio);
    }

    private boolean descuentoCumpleanos(Reserva reserva) {
        LocalDate fechaReserva = reserva.getFechaReserva();
        String fechaReservaFormateada = formateoFechas(fechaReserva);
        LocalDateTime fechaNacimiento = this.repositorioReserva.consultarFechaNacimiento(reserva.getIdUsuario());
        String fechaNacimientoFormateada = formateoFechas(fechaNacimiento.toLocalDate());
        boolean estaDeCumpleaños = fechaNacimientoFormateada.equals(fechaReservaFormateada);
        reserva.setObsequio(!estaDeCumpleaños);
        return estaDeCumpleaños;
    }


    private String formateoFechas(LocalDate fecha) {
        return fecha.getDayOfMonth() + "/" + fecha.getMonth().toString();
    }


}
