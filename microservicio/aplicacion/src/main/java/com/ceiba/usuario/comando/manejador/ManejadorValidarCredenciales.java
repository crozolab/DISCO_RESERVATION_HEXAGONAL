package com.ceiba.usuario.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.comando.fabrica.FabricaUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.ServicioValidarSesion;
import org.springframework.stereotype.Component;

@Component
public class ManejadorValidarCredenciales implements ManejadorComandoRespuesta<ComandoUsuario, ComandoRespuesta<Integer>> {

    private final FabricaUsuario fabricaUsuario;
    private final ServicioValidarSesion servicioValidarSesion;

    public ManejadorValidarCredenciales(FabricaUsuario fabricaUsuario, ServicioValidarSesion servicioValidarSesion) {
        this.fabricaUsuario = fabricaUsuario;
        this.servicioValidarSesion = servicioValidarSesion;
    }


    public ComandoRespuesta<Integer> ejecutar(ComandoUsuario comandoUsuario) {
        Usuario usuario = this.fabricaUsuario.crearUsuarioLogin(comandoUsuario);
         return new ComandoRespuesta<> (this.servicioValidarSesion.ejecutar(usuario));
    }
}
