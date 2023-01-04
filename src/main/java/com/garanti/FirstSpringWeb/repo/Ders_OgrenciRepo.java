package com.garanti.FirstSpringWeb.repo;

import com.garanti.FirstSpringWeb.Constants;
import com.garanti.FirstSpringWeb.model.Ders;
import com.garanti.FirstSpringWeb.model.Ders_Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class Ders_OgrenciRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders_Ogrenci> getAll()
    {
        return jdbcTemplate.query("select * from BILGE.DERS_OGRENCI", BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

    public Ders_Ogrenci getById(int id)
    {
        String sql = "select * from BILGE.DERS_OGRENCI where ID = :ID";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ID",id);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

    public boolean deleteById(int id)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID",id);
        return namedParameterJdbcTemplate.update("delete from BILGE.DERS_OGRENCI where ID = :ID",mapSqlParameterSource) == 1;

    }

    public boolean save(Ders_Ogrenci dersOgrenci)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("DERS_ID",dersOgrenci.getDERS_ID());
        mapSqlParameterSource.addValue("OGRENCI_ID",dersOgrenci.getOGRENCI_ID());
        mapSqlParameterSource.addValue("NOTE",dersOgrenci.getNOTE());
        mapSqlParameterSource.addValue("DEVAMSIZLIK",dersOgrenci.getDEVAMSIZLIK());
        return namedParameterJdbcTemplate.update("Insert into BILGE.DERS_OGRENCI (DERS_ID, OGRENCI_ID, NOTE, DEVAMSIZLIK) " +
                        "values (:DERS_ID, :OGRENCI_ID, :NOTE, :DEVAMSIZLIK)",mapSqlParameterSource) == 1;


    }
}