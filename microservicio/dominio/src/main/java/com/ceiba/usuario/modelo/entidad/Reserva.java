package com.ceiba.usuario.modelo.entidad;




import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

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
        setId(id);
        setNombre(nombre);
        setFechaReserva(fechaReserva);
        setCategoria(categoria);
        setIdUsuario(idUsuario);
        setPrecio(precio);
        setObsequio(obsequio);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isObsequio() {
        return obsequio;
    }

    public void setObsequio(boolean obsequio) {
        this.obsequio = obsequio;
    }
}
