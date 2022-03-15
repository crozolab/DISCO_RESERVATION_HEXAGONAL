package com.ceiba.usuario.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.servicio.ServicioEliminarReserva;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarReserva implements ManejadorComando<Integer> {

    private final ServicioEliminarReserva servicioEliminarReserva;

    public ManejadorEliminarReserva(ServicioEliminarReserva servicioEliminarReserva) {
        this.servicioEliminarReserva = servicioEliminarReserva;
    }


    public void ejecutar(Integer idUsuario) {
        this.servicioEliminarReserva.ejecutar(idUsuario);
    }
}
