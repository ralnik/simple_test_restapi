package ru.example.sbertest.db.dao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.dao.question.QuestionDAO;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.db.entities.Test;
import ru.example.sbertest.exception.ApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDAOImpl implements TestDAO {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Test> findAll() {
        return null;
    }

    @Override
    public Test findById(Long id) {
        String sql = "select * from test where id=?";
        return jdbcTemplate.query(sql, getRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(Test object) {

    }

    @Override
    public void update(Test object) {

    }

    @Override
    public void delete(Test object) {

    }

    @Override
    public Test findByName(String name) {
        String sql = "select * from test where name=?";
        return jdbcTemplate.query(sql, getRowMapper(), name)
                .stream()
                .findFirst()
                .orElseThrow(ApiException::new);
    }

    private RowMapper<Test> getRowMapper() {
        return new RowMapper<Test>() {
            @Override
            public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
                Test test = new Test();
                test.setId(rs.getLong("id"));

                String[] listQuestion = rs.getString("questions").split(",");
                List<Question> questions = new ArrayList<>();
                for (String item : listQuestion) {
                    Question question = questionDAO.findById(Long.valueOf(item));
                    questions.add(question);
                }

                test.setQuestion(questions);
                test.setName(rs.getString("name"));
                return test;
            }
        };
    }
}
