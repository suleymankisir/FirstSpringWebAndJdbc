package com.garanti.FirstSpringWeb.repo;


import com.garanti.FirstSpringWeb.model.Ogrenci;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class OgrenciRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Ogrenci> getaAll() {
        return jdbcTemplate.query("select * from BILGE.OGRENCI", new RowMapper<Ogrenci>() {
            @Override
            public Ogrenci mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Ogrenci(result.getInt("ID"), result.getString("NAME"),
                        result.getInt("OGR_NUMBER"), result.getInt("YEAR"));
            }
        });
    }

    public Ogrenci getById(int id)
    {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ID",id);
        return namedParameterJdbcTemplate.queryForObject("select * from BILGE.OGRENCI where ID = :ID",map,
                BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }

    public boolean deleteById( int id){

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ID",id);
        return namedParameterJdbcTemplate.update("delete from BILGE.OGRETMEN where ID = :ID",map) == 1;

    }

    public boolean save ( Ogrenci ogrenci)
    {
        MapSqlParameterSource map = new MapSqlParameterSource();
        String sql = "Insert into BILGE.OGRENCI (NAME,OGR_NUMBER,YEAR) values (:NAME, :OGR_NUMBER, :YEAR)";
        map.addValue("NAME",ogrenci.getNAME());
        map.addValue("OGR_NUMBER",ogrenci.getOGR_NUMBER());
        map.addValue("YEAR",ogrenci.getYEAR());
        return namedParameterJdbcTemplate.update(sql,map) == 1;
    }
    public List<Ogrenci> getAllLike(String name)
    {
        String sql = "select * from BILGE.OGRENCI where NAME LIKE :NAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", "%" + name + "%");
        return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }
}
