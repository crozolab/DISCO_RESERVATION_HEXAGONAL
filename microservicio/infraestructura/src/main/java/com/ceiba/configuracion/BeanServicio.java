package com.ceiba.configuracion;

import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioValidarSesion servicioValidarSesion (RepositorioUsuario repositorioUsuario) {
        return new ServicioValidarSesion(repositorioUsuario);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva (RepositorioReserva repositorioReserva) {
        return new ServicioCrearReserva(Clock.systemDefaultZone(), repositorioReserva);
    }

    @Bean
    public ServicioEliminarReserva servicioEliminarReserva (RepositorioReserva repositorioReserva) {
        return new ServicioEliminarReserva(repositorioReserva);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
