package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private String nombre;
    private String categoria;
    private LocalDate fechaReserva;
    private int idUsuario;
    private float precio;
    private boolean obsequio;

    public ComandoReservaTestDataBuilder() {


        nombre = "camilo";
        categoria = "vip";
        fechaReserva = LocalDate.parse("2022-11-14");
        idUsuario = 2;
        precio = 80000;
        obsequio = true;

    }

    public ComandoReserva build() {
        return new ComandoReserva(id,nombre,categoria, fechaReserva, idUsuario, precio, obsequio);
    }

}
