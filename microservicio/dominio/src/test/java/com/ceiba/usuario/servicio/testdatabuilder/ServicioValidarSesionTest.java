package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionCredencialesIncorrectas;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioValidarSesion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioValidarSesionTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario Login con error")
    void deberiaValidarLaExistenciaPreviaDelUsuarioLoginError() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().conId(1L).buildLogin();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existenCredenciales(Mockito.any())).thenReturn(false);
        ServicioValidarSesion servicioValidarSesion = new ServicioValidarSesion(repositorioUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioValidarSesion.ejecutar(usuario), ExcepcionCredencialesIncorrectas.class, "El usuario o contraseña ingresados son incorrectos");
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario Login")
    void deberiaValidarLaExistenciaPreviaDelUsuarioLogin() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().conId(1L).buildLogin();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existenCredenciales(Mockito.any())).thenReturn(true);
        Mockito.when(repositorioUsuario.obtenerId(Mockito.any())).thenReturn(1);
        ServicioValidarSesion servicioValidarSesion = new ServicioValidarSesion(repositorioUsuario);
        // act
        int idUsuario = servicioValidarSesion.ejecutar(usuario);
        // assert
        assertEquals(1, idUsuario);
    }


}
