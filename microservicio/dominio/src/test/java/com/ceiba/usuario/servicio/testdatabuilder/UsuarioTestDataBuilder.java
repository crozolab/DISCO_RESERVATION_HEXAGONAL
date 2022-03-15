package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombreUsuario;
    private String clave;
    private LocalDateTime fecha;
    private LocalDate fechaNacimiento;
    ;

    public UsuarioTestDataBuilder() {
        nombreUsuario = "1234";
        clave = "1234";
        fecha = LocalDateTime.now();
        fechaNacimiento = LocalDate.parse("1992-11-06");
    }


    public UsuarioTestDataBuilder conClave(String clave) {
        this.clave = clave;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fecha = fechaCreacion;
        return this;
    }

    public UsuarioTestDataBuilder conNombre(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public UsuarioTestDataBuilder conFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nombreUsuario, clave, fecha, fechaNacimiento);
    }

    public Usuario buildLogin() {
        return new Usuario(nombreUsuario, clave);
    }

}
