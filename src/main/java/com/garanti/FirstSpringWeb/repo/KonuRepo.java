package com.garanti.FirstSpringWeb.repo;


import com.garanti.FirstSpringWeb.model.Konu;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.List;


@Repository
@AllArgsConstructor
public class KonuRepo {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Konu> getAll()
    {
        return jdbcTemplate.query("select * from BILGE.KONU", BeanPropertyRowMapper.newInstance(Konu.class));

    }

    public Konu getById(int id)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID",id);
        return namedParameterJdbcTemplate.queryForObject("select * from BILGE.KONU where ID = :ID", mapSqlParameterSource, new RowMapper<Konu>() {
            @Override
            public Konu mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Konu(result.getInt("ID"), result.getString("NAME"));
            }
        });
    }

    public boolean deleteById(int id)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID",id);
        return namedParameterJdbcTemplate.update("delete from BILGE.KONU where ID = :ID",
                mapSqlParameterSource) == 1;
    }

    public boolean save(Konu konu)
    {
        String sql = "Insert into BILGE.KONU (NAME) values (:NAME)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NAME",konu.getNAME());
        return namedParameterJdbcTemplate.update(sql,mapSqlParameterSource) == 1;

    }
    public List<Konu> getByLike(String name){
        String sql = "select * from BILGE.OGRENCI where NAME LIKE :NAME";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NAME","%"+name+"%");
        return namedParameterJdbcTemplate.query(sql,mapSqlParameterSource,BeanPropertyRowMapper.newInstance(Konu.class));
    }
}
