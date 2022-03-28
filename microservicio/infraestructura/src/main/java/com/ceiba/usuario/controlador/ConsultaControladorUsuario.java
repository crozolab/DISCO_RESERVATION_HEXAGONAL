package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.ComandoRespuesta;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.comando.manejador.ManejadorValidarCredenciales;
import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;
    private final ManejadorValidarCredenciales manejadorValidarCredenciales;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios, ManejadorValidarCredenciales manejadorValidarCredenciales) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
        this.manejadorValidarCredenciales = manejadorValidarCredenciales;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }



    @PutMapping
    @ApiOperation("Validar password")
    public ComandoRespuesta<Integer> validar(@RequestBody ComandoUsuario comandoUsuario) {
        return manejadorValidarCredenciales.ejecutar(comandoUsuario);
    }

}
