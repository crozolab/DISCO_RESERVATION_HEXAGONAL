package com.ceiba.usuario.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
    private String nombre;
    private String categoria;
    private LocalDate fechaReserva;
    private int idUsuario;
    private float precio;
    private boolean obsequio;
}
