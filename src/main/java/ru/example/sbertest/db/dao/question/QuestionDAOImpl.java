package ru.example.sbertest.db.dao.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.dao.typequestion.TypeQuestionDAO;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.db.entities.TypeQuestion;
import ru.example.sbertest.exception.ApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TypeQuestionDAO typeQuestionDAO;

    @Override
    public List<Question> findAll() {
        String sql = "SELECT * FROM question";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public void create(Question question) {
        String sqlInsert = "INSERT INTO question (question, type_id)"
                + " VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, question.getQuestion(), question.getType().getId());
    }

    @Override
    public void update(Question question) {
        String sqlUpdate = "UPDATE question set question=?, type_id=? where id=?";
        jdbcTemplate.update(sqlUpdate, question.getQuestion(), question.getType().getId(), question.getId());
    }

    @Override
    public void delete(Question question) {
        String sqlDelete = "DELETE FROM question WHERE id=?";
        jdbcTemplate.update(sqlDelete, question.getId());
    }

    @Override
    public Question findById(Long id) {
        String sql = "select * from question where id=?";
        return jdbcTemplate.query(sql, getRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Question findByQuestionAndTypeId(String question, Long typeId) {
        String sql = "select * from question where question=? and type_id=?";
        return jdbcTemplate.query(sql, getRowMapper(), question, typeId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Question save(Question question) {
        create(question);
        return findByQuestionAndTypeId(question.getQuestion(), question.getType().getId());
    }

    private RowMapper<Question> getRowMapper() {
        return new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getLong("id"));
                question.setQuestion(rs.getString("question"));

                TypeQuestion type = Optional.ofNullable(typeQuestionDAO.findById(rs.getLong("type_id")))
                        .orElseThrow(ApiException::new);
                question.setType(type);
                return question;
            }
        };
    }
}
