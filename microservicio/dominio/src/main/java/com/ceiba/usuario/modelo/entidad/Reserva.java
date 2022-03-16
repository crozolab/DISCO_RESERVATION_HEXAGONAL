package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
@Setter
@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_RESERVA = "Se debe ingresar la fecha de la reserva";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_LA_CATEGORIA_DE_LA_RESERVA = "Se debe ingresar la categoria de la reserva";

    private Long id;
    private String nombre;
    private String categoria;
    private LocalDate fechaReserva;
    private int idUsuario;
    private float precio;
    private boolean obsequio;

    public Reserva(Long id, String nombre, String categoria, LocalDate fechaReserva, int idUsuario, float precio, boolean obsequio) {
        validarObligatorio(categoria, SE_DEBE_INGRESAR_LA_CATEGORIA_DE_LA_RESERVA);
        validarObligatorio(fechaReserva, SE_DEBE_INGRESAR_LA_FECHA_DE_RESERVA);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        this.id = id;
        this.nombre = nombre;
        this.fechaReserva = fechaReserva;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.precio = precio;
        this.obsequio = obsequio;
    }

}
