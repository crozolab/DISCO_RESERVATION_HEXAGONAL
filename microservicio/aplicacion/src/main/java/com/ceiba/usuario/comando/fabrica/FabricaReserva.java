package com.ceiba.usuario.comando.fabrica;

import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;


@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(
                comandoReserva.getId(),
                comandoReserva.getNombre(),
                comandoReserva.getCategoria(),
                comandoReserva.getFechaReserva(),
                comandoReserva.getIdUsuario(),
                comandoReserva.getPrecio(),
                comandoReserva.isObsequio()
        );
    }

}
