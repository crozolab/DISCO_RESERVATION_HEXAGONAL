package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public interface RepositorioReserva {
    /**
     * Permite crear un usuario
     * @param reserva
     * @return el id generado
     */

    Long crear(Reserva reserva);


    /**
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Integer id);


    /**
     * Permite validar si existe un usuario con un nombre
     * @param idUsuario
     * @return si existe o no
     */
    LocalDateTime consultarFechaNacimiento(int idUsuario);


}
