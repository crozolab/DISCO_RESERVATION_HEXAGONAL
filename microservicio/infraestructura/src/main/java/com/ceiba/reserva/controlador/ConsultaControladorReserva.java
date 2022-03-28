package com.ceiba.reserva.controlador;

import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.consulta.ManejadorListarReservas;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Reserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@Api(tags={"Controlador consulta reserva"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas) {
        this.manejadorListarReservas = manejadorListarReservas;
    }

    @GetMapping
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar() {
        return this.manejadorListarReservas.ejecutar();
    }



    @PostMapping ("/usuario")
    @ApiOperation("Mostrar las reservas del usuario")
    public List<DtoReserva> listarReservasUsuario(@RequestBody ComandoReserva comandoReserva){
        return this.manejadorListarReservas.ejecutar(comandoReserva);
    }
}
