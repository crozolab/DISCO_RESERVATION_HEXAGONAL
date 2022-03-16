package com.ceiba.usuario.consulta;

import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservas {

    private final DaoReserva daoReserva;

    public ManejadorListarReservas(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoReserva> ejecutar() {
        return this.daoReserva.listar();
    }

    public List<DtoReserva> ejecutar(ComandoReserva comandoReserva) {
        return this.daoReserva.listarReservaUsario(comandoReserva.getIdUsuario());
    }
}
