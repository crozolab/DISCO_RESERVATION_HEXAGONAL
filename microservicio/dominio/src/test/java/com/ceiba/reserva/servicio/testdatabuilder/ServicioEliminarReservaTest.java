package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarReserva;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarReservaTest {

    @Test
    @DisplayName("Deberia eliminar el usuario llamando al repositorio")
    void deberiaEliminarLaReservaLlamandoAlRepositorio() {
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva);

        servicioEliminarReserva.ejecutar(1);

        Mockito.verify(repositorioReserva, Mockito.times(1)).eliminar(1);

    }

}
