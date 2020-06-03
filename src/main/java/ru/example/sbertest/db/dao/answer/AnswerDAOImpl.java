package ru.example.sbertest.db.dao.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.dao.question.QuestionDAO;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.utils.BooleanUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDAOImpl implements AnswerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public List<Answer> findAll() {
        String sql = "SELECT * FROM answer";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public void create(Answer answer) {
        String sqlInsert = "INSERT INTO answer (answer, question_id, iscorrect)"
                + " VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlInsert,
                answer.getAnswer(),
                answer.getQuestion().getId(),
                BooleanUtils.toInt(answer.getIsCorrect()));
    }

    @Override
    public void update(Answer answer) {
        String sqlUpdate = "UPDATE answer set answer=?, question_id=?, iscorrect=? where id=?";
        jdbcTemplate.update(sqlUpdate,
                answer.getAnswer(),
                answer.getQuestion().getId(),
                BooleanUtils.toInt(answer.getIsCorrect()),
                answer.getId());
    }

    @Override
    public void delete(Answer answer) {
        String sqlDelete = "DELETE FROM answer WHERE id=?";
        jdbcTemplate.update(sqlDelete, answer.getId());
    }

    @Override
    public Answer findById(Long id) {
        String sql = "select * from answer where id=?";
        return jdbcTemplate.query(sql, getRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Answer findByQuestionId(Long id) {
        String sql = "select * from answer where question_id=?";
        return jdbcTemplate.query(sql, getRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    private RowMapper<Answer> getRowMapper() {
        return new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Answer answer = new Answer();
                answer.setId(rs.getLong("id"));
                answer.setAnswer(rs.getString("answer"));

                Question question = Optional.ofNullable(questionDAO.findById(rs.getLong("question_id")))
                        .orElseThrow(ApiException::new);
                answer.setQuestion(question);
                answer.setIsCorrect(rs.getBoolean("iscorrect"));
                return answer;
            }
        };
    }
}
