package ru.example.sbertest.db.dao.typequestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.entities.TypeQuestion;

import java.util.List;

@Repository
public class TypeQuestionDAOImpl implements TypeQuestionDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TypeQuestion> findAll() {
        String sql = "SELECT * FROM typequestion";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TypeQuestion.class));
    }

    @Override
    public void create(TypeQuestion typeQuestion) {
        String sqlInsert = "INSERT INTO typequestion (name)"
                + " VALUES (?)";
        jdbcTemplate.update(sqlInsert, typeQuestion.getName());
    }

    @Override
    public void update(TypeQuestion typeQuestion) {
        String sqlUpdate = "UPDATE typequestion set name=? where id=?";
        jdbcTemplate.update(sqlUpdate, typeQuestion.getName(), typeQuestion.getId());
    }

    @Override
    public void delete(TypeQuestion typeQuestion) {
        String sqlDelete = "DELETE FROM typequestion WHERE id=?";
        jdbcTemplate.update(sqlDelete, typeQuestion.getId());
    }

    @Override
    public TypeQuestion findById(Long id) {
        String sql = "select * from typequestion where id=?";
        RowMapper<TypeQuestion> rowMapper = new BeanPropertyRowMapper<>(TypeQuestion.class);
        return jdbcTemplate.query(sql,rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

}
