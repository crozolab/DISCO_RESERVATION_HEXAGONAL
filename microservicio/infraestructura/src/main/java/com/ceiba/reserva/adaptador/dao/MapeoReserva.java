package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoReserva;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String categoria = resultSet.getString("categoria");
        LocalDate fechaReserva = extraerLocalDate(resultSet, "fecha_reserva");
        int idUsuario = resultSet.getInt("id_usuario");
        float precio = resultSet.getFloat("precio");
        boolean obsequio = resultSet.getBoolean("obsequio");
        return new DtoReserva(id,nombre,categoria,fechaReserva, idUsuario, precio, obsequio);
    }

}
