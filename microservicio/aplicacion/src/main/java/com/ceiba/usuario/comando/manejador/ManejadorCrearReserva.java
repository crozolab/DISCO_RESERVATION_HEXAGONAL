package com.ceiba.usuario.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.comando.fabrica.FabricaReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.servicio.ServicioCrearReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearReserva implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<Long>> {

    private final FabricaReserva fabricaReserva;
    private final ServicioCrearReserva servicioCrearReserva;

    public ManejadorCrearReserva(FabricaReserva fabricaReserva, ServicioCrearReserva servicioCrearReserva) {
    this.fabricaReserva = fabricaReserva;
    this.servicioCrearReserva = servicioCrearReserva;
    }


    public ComandoRespuesta<Long> ejecutar(ComandoReserva comandoReserva) {
        Reserva reserva = this.fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(this.servicioCrearReserva.ejecutar(reserva));
    }
}
