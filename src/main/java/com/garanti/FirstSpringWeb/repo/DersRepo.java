package com.garanti.FirstSpringWeb.repo;


import com.garanti.FirstSpringWeb.model.Ders;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DersRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<Ders> getAll()
    {
        return jdbcTemplate.query("select * from BILGE.DERS", BeanPropertyRowMapper.newInstance(Ders.class));
    }

    public Ders getById(int id)
    {
        String sql = "select * from BILGE.DERS where ID = :ID";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("ID",id);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Ders.class));
    }

    public boolean deleteById(int id)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID",id);
        return namedParameterJdbcTemplate.update("delete from BILGE.DERS where ID = :ID",mapSqlParameterSource) == 1;

    }

    public boolean save(Ders ders)
    {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("OGR_ID",ders.getOGR_ID());
        mapSqlParameterSource.addValue("KONU_ID",ders.getKONU_ID());
        return namedParameterJdbcTemplate.update("Insert into BILGE.DERS (OGR_ID,KONU_ID) values (:OGR_ID, :KONU_ID)"
                ,mapSqlParameterSource) == 1;

    }
}
