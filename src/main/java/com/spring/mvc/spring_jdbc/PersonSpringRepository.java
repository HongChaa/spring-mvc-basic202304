package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSpringRepository {

    // 스프링 JDBC 활용 - 데이터베이스 접속 설정 정보를
    // 설정파일을 통해 불러와서 사용합니다.
    private final JdbcTemplate jdbcTemplate;

    // 저장 기능
    public void savePerson(Person p) {
        String sql = "INSERT INTO person" +
                "(person_name, person_age)" +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql, p.getPersonName(), p.getPersonAge());
    }

    // 삭제 기능
    public void removePerson(long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 수정 기능
    public void updatePerson(long id, String name, int age) {
        String sql = "UPDATE person SET PERSON_NAME = ?, PERSON_AGE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, name, age, id);
    }

    // 전체 조회 기능
    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Person(rs));
    }

    // 개별 조회 기능
    public Person findOne(long id) {
        String sql = "SELECT * FROM WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, n) -> new Person(rs), id);
    }



}
