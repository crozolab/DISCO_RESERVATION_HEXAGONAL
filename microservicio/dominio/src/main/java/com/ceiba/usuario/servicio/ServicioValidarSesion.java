package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionCredencialesIncorrectas;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioValidarSesion {
    private static final String USUARIO_CONTRASENA_INCORRECTO = "El usuario o contraseña ingresados son incorrectos";
    private final RepositorioUsuario repositorioUsuario;

    public ServicioValidarSesion(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public int ejecutar(Usuario usuario) {
        validarUsuario(usuario);
        return obtenerId(usuario);
    }


    private void validarUsuario(Usuario usuario) {
        boolean credencialesValidas = this.repositorioUsuario.existenCredenciales(usuario);
        if (!credencialesValidas) {
            throw new ExcepcionCredencialesIncorrectas(USUARIO_CONTRASENA_INCORRECTO);
        }
    }

    private int obtenerId(Usuario usuario) {
        return this.repositorioUsuario.obtenerId(usuario);
    }
}
