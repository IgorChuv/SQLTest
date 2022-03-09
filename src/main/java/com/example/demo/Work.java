package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Work {
    private final JdbcTemplate jdbcTemplate;
    private final RoleRepository roleRepository;

    public Work(JdbcTemplate jdbcTemplate, RoleRepository roleRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public List<?> getSome() {
        String sql = "SELECT * FROM fable";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new TestObject(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("surname")));
    }

    @GetMapping("/1")
    public String getSome1() {


        return roleRepository.findById(1).getName();
    }

    @GetMapping("/2")
    public List<?> getSome2() {
        List<Long> list = List.of(1L, 2L);

        return roleRepository.findAllByIdIn(list);
    }

    @GetMapping("/3")
    public List<?> getSome3() {
        String query = "select rp.id,rp.name from fable t inner join table_name1 u on table_name.id=table_name1.id WHERE t.id in";
        List<Long> list = List.of(1L, 2L);
        return roleRepository.findAllByNameAndIdIn("n2", list);
    }

    @GetMapping("/4")
    public List<?> getSome4() {


        String num = " (1,2)";
        String query = "select t.colid,t.colname from table_name1 t inner join fable u on u.id=t.colid where u.id in" + num;

        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        Arrays.asList(
                                rs.getLong("colid"),
                                rs.getString("colname")));

    }
    @GetMapping("/5")
    public List<?> getSome5() {
        List<Long> list = List.of(2L,3L);
        String inSql = String.join(",", Collections.nCopies(list.size(), "?"));



        return jdbcTemplate.query(String.format("select t.colid,t.colname from table_name1 t inner join fable u on u.id=t.colid where u.id in (%s)",
                inSql),list.toArray(),
                (rs, rowNum) ->
                        Arrays.asList(
                                rs.getLong("colid"),
                                rs.getString("colname")));

    }
    @GetMapping("/6")
    public List<?> getSome6() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        String inParams = ids.stream().map(id -> "?").collect(Collectors.joining(","));

        return jdbcTemplate.query(String.format("select t.colid,t.colname from table_name1 t inner join fable u on u.id=t.colid where u.id in (%s)",
                inParams),ids.toArray(),
                (rs, rowNum) ->
                        Arrays.asList(
                                rs.getLong("colid"),
                                rs.getString("colname")));

    }

}






