package com.ceiba.usuario.modelo.entidad;


import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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
        validarCategoria(categoria);
        this.id = id;
        this.nombre = nombre;
        this.fechaReserva = fechaReserva;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
        this.precio = precio;
        this.obsequio = obsequio;
    }


    private void validarCategoria(String categoria) {
        boolean categoriaValida = categoria.equalsIgnoreCase(CATEGORIA_GENERAL) || categoria.equalsIgnoreCase(CATEGORIA_VIP);
        if (!categoriaValida) {
            throw new ExcepcionValorInvalido(CATEGORIA_ERRONEA);
        }
    }

    public void asignarPrecio(String categoria, boolean descuento, boolean cumpleanos) {
         precio = (categoria.equalsIgnoreCase(CATEGORIA_VIP)) ? PRECIO_VIP : PRECIO_GENERAL;
        if (descuento && cumpleanos) {
            precio = (float) (precio * (1 - (DESCUENTO_DIAS + DESCUENTO_CUMPLEANOS)));
        } else if (descuento) {
            precio = (float) (precio * (1 - DESCUENTO_DIAS));
        } else if (cumpleanos) {
            precio = (float) (precio * (1 - DESCUENTO_CUMPLEANOS));
        }
        setPrecio(precio);
    }

    public String formateoFechas(LocalDate fecha) {
        return fecha.getDayOfMonth() + "/" + fecha.getMonth().toString();
    }


}
