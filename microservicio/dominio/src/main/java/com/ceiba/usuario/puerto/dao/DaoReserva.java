package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {

    /**
     * Permite listar reservas
     * @return los reservas
     */
    List<DtoReserva> listar();


    /**
     * Permite listar reservas de los usuarios concretos
     * @return los reservas
     * @param idUsuario
     */
    List<DtoReserva> listarReservaUsario(Integer idUsuario);
}
