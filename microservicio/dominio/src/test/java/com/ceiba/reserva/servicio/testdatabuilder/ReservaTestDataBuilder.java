package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private String nombre;
    private String categoria;
    private LocalDate fechaReserva;
    private int idUsuario;
    private float precio;

    public ReservaTestDataBuilder() {
        nombre = "1234";
        categoria = "vip";
        fechaReserva = LocalDate.now();
        idUsuario = 1;
        precio = 80000;
    }

    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }


    public ReservaTestDataBuilder conCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ReservaTestDataBuilder conIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public ReservaTestDataBuilder conFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }

    public ReservaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ReservaTestDataBuilder conPrecio(float precio) {
        this.precio = precio;
        return this;
    }


    public Reserva build() {
        return new Reserva(id, nombre, categoria, fechaReserva, idUsuario, precio);
    }


}
