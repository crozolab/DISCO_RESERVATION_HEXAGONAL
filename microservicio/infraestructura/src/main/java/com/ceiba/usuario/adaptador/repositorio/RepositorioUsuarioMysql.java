package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioMysql implements RepositorioUsuario {
    private final String NOMBRE = "nombre";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    @SqlStatement(namespace = "usuario", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "usuario", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "usuario", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "usuario", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "usuario", value = "existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "usuario", value = "existenCredenciales")
    private static String sqlexistenCredenciales;

    @SqlStatement(namespace = "usuario", value = "obtenerId")
    private static String sqlobtenerId;

    public RepositorioUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Usuario usuario) {
        return this.customNamedParameterJdbcTemplate.crear(usuario, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NOMBRE, nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste, paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Usuario usuario) {
        this.customNamedParameterJdbcTemplate.actualizar(usuario, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId, paramSource, Boolean.class);
    }

    @Override
    public boolean existenCredenciales(Usuario usuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NOMBRE, usuario.getNombre());
        paramSource.addValue("clave", usuario.getClave());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlexistenCredenciales, paramSource, Boolean.class);
    }

    @Override
    public int obtenerId(Usuario usuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NOMBRE, usuario.getNombre());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlobtenerId, paramSource, int.class);
    }

}
