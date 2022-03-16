package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Usuario {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";
    private static final String LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_LA_CLAVE = "Se debe ingresar la clave";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_EL_FECHA_DE_NACIMIENTO = "Se debe ingresar la fecha de nacimiento";
    private static final int LONGITUD_MINIMA_CLAVE = 4;

    private Long id;
    private final String nombre;
    private final String clave;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaNacimiento;

    public Usuario(Long id, String nombre, String clave, LocalDateTime fechaCreacion, LocalDate fechaNacimiento) {
        validarObligatorio(clave, SE_DEBE_INGRESAR_LA_CLAVE);
        validarLongitud(clave, LONGITUD_MINIMA_CLAVE, String.format(LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_CLAVE));
        validarObligatorio(fechaCreacion, SE_DEBE_INGRESAR_LA_FECHA_CREACION);
        validarObligatorio(fechaNacimiento, SE_DEBE_INGRESAR_EL_FECHA_DE_NACIMIENTO);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);

        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario(String nombre, String clave) {
        validarLongitud(clave, LONGITUD_MINIMA_CLAVE, String.format(LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_CLAVE));
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        this.nombre = nombre;
        this.clave = clave;
    }
}
