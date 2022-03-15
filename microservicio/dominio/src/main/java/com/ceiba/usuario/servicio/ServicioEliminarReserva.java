package com.ceiba.usuario.servicio;

import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;

public class ServicioEliminarReserva {

    private final RepositorioReserva repositorioReserva;

    public ServicioEliminarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Integer id) {
        this.repositorioReserva.eliminar(id);
    }
}
