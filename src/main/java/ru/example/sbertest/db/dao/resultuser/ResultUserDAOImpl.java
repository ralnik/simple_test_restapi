package ru.example.sbertest.db.dao.resultuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.dao.answer.AnswerDAO;
import ru.example.sbertest.db.dao.answer.AnswerDAOImpl;
import ru.example.sbertest.db.dao.test.TestDAO;
import ru.example.sbertest.db.dao.test.TestDAOImpl;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.dao.user.UserDAOImpl;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.ResultUser;
import ru.example.sbertest.db.entities.Test;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.db.response.GetUserTestDoneInProcent;
import ru.example.sbertest.exception.ApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultUserDAOImpl implements ResultUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TestDAO testDAO;
    @Autowired
    private AnswerDAO answerDAO;


    @Override
    public List<ResultUser> findAll() {
        String sql = "select * from resultuser";

        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public void create(ResultUser object) {
        String sqlInsert = "INSERT INTO resultuser (test_id, answer_id, self_answer, user_id, mistake)"
                + " VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sqlInsert,
                object.getTest().getId(),
                object.getAnswer().getId(),
                object.getSelfAnswer(),
                object.getUser().getId(),
                object.getMistake());
    }

    @Override
    public void update(ResultUser object) {
        String sqlUpdate = "UPDATE resultuser set test_id=?, answer_id=?, self_answer=?, user_id=?, mistake=? where id=?";
        jdbcTemplate.update(sqlUpdate,
                object.getTest(),
                object.getAnswer().getId(),
                object.getSelfAnswer(),
                object.getUser(),
                object.getMistake(),
                object.getId());
    }

    @Override
    public void delete(ResultUser object) {
        String sqlDelete = "DELETE FROM resultuser WHERE id=?";
        jdbcTemplate.update(sqlDelete, object.getId());
    }

    @Override
    public ResultUser findById(Long id) {
        String sql = "select * from resultuser where id=?";
        RowMapper<ResultUser> rowMapper = new BeanPropertyRowMapper<>(ResultUser.class);
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ResultUser> findByUserToken(Integer userToken) {
        String sql = "select resultuser.* from resultuser join user on resultuser.user_id=user.id \n" +
                "where user.userToken=?";
        return jdbcTemplate.query(sql, getRowMapper(), userToken);
    }

    @Override
    public List<ResultUser> findByNameAndUserToken(String name, Integer userToken) {
        String sql = "select resultuser.* from resultuser join user on resultuser.user_id=user.id \n" +
                " join test on resultuser.test_id=test.id \n" +
                " where user.userToken=? and test.name=?";
        return jdbcTemplate.query(sql, getRowMapper(), userToken, name);
    }

    @Override
    public List<String> findUsersTestDone() {
        String sql = "SELECT distinct username FROM user  left join resultuser on resultuser.user_id=user.id \n" +
                "where resultuser.id is not null";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public List<GetUserTestDoneInProcent> getUserTestDoneInProcent() {
        String sql = "select test_id, user_id, test.name as testname, user.username, " +
                "count(answer_id) as countQuestion, sum(mistake) as mistake, \n" +
                "(count(answer_id)-sum(mistake))*100/count(answer_id) as procent\n" +
                "from resultuser join test on resultuser.test_id=test.id\n" +
                "join user on resultuser.user_id=user.id\n" +
                "group by test_id, user_id";
        RowMapper<GetUserTestDoneInProcent> rowMapper = new RowMapper<GetUserTestDoneInProcent>() {
            @Override
            public GetUserTestDoneInProcent mapRow(ResultSet rs, int rowNum) throws SQLException {
                GetUserTestDoneInProcent getUserTestDoneInProcent = new GetUserTestDoneInProcent();
                getUserTestDoneInProcent.setTest(rs.getString("testname"));
                getUserTestDoneInProcent.setUser(rs.getString("username"));
                getUserTestDoneInProcent.setCountQuestion(rs.getInt("countQuestion"));
                getUserTestDoneInProcent.setCountMistake(rs.getInt("mistake"));
                getUserTestDoneInProcent.setProcent(rs.getString("procent") + "%");
                return getUserTestDoneInProcent;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<GetUserTestDoneInProcent> getUserTestDoneInProcentByUserToken(Integer userToken) {
        String sql = "select test_id, user_id, test.name as testname, user.username, " +
                "count(answer_id) as countQuestion, sum(mistake) as mistake, \n" +
                "(count(answer_id)-sum(mistake))*100/count(answer_id) as procent\n" +
                "from resultuser join test on resultuser.test_id=test.id\n" +
                "join user on resultuser.user_id=user.id\n " +
                "where user.userToken=? \n" +
                "group by test_id, user_id";

        RowMapper<GetUserTestDoneInProcent> rowMapper = new RowMapper<GetUserTestDoneInProcent>() {
            @Override
            public GetUserTestDoneInProcent mapRow(ResultSet rs, int rowNum) throws SQLException {
                GetUserTestDoneInProcent getUserTestDoneInProcent = new GetUserTestDoneInProcent();
                getUserTestDoneInProcent.setTest(rs.getString("testname"));
                getUserTestDoneInProcent.setUser(rs.getString("username"));
                getUserTestDoneInProcent.setCountQuestion(rs.getInt("countQuestion"));
                getUserTestDoneInProcent.setCountMistake(rs.getInt("mistake"));
                getUserTestDoneInProcent.setProcent(rs.getString("procent") + "%");
                return getUserTestDoneInProcent;
            }
        };
        return jdbcTemplate.query(sql, rowMapper, userToken);
    }

    private RowMapper<ResultUser> getRowMapper() {
        return new RowMapper<ResultUser>() {
            @Override
            public ResultUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResultUser resultUser = new ResultUser();
                resultUser.setId(rs.getLong("id"));

                Test test = Optional.ofNullable(testDAO.findById(rs.getLong("test_id")))
                        .orElseThrow(ApiException::new);
                resultUser.setTest(test);

                Answer answer = Optional.ofNullable(answerDAO.findById(rs.getLong("answer_id")))
                        .orElseThrow(ApiException::new);
                resultUser.setAnswer(answer);

                resultUser.setSelfAnswer(rs.getString("self_answer"));

                User user = Optional.ofNullable(userDAO.findById(rs.getLong("user_id")))
                        .orElseThrow(ApiException::new);
                resultUser.setUser(user);
                resultUser.setMistake(rs.getBoolean("mistake"));
                return resultUser;
            }
        };
    }
}
