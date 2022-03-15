package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String clave;
    private LocalDateTime fecha;
    private LocalDate fechaNacimiento;

    public ComandoUsuarioTestDataBuilder() {
        nombre = UUID.randomUUID().toString();
        clave = "1234";
        fecha = LocalDateTime.now();
        fechaNacimiento = LocalDate.parse("1992-11-06");
    }

    public ComandoUsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoUsuario build(String nombre) {
        return new ComandoUsuario(id,nombre, clave,fecha, fechaNacimiento);
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id,nombre, clave,fecha, fechaNacimiento);
    }

}
