package ru.example.sbertest.db.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.example.sbertest.db.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findById(Long id) {
        String sql = "select * from user where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(User user) {
        String sqlInsert = "INSERT INTO user (username, password, userToken)"
                + " VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlInsert, user.getUsername(), user.getPassword(), user.getUserToken());
    }

    @Override
    public void update(User user) {
        String sqlUpdate = "UPDATE user set username=?, password=? where userToken=?";
        jdbcTemplate.update(sqlUpdate, user.getUsername(), user.getPassword(), user.getUserToken());
    }

    @Override
    public void delete(User user) {
        String sqlDelete = "DELETE FROM user WHERE username=? and password=? and userToken=?";
        jdbcTemplate.update(sqlDelete, user.getUsername(), user.getPassword(), user.getUserToken());
    }

    @Override
    public User findByUserToken(Integer userToken) {
        String sql = "select * from user where userToken=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return jdbcTemplate.query(sql, rowMapper, userToken)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        String sql = "select id,username,password,userToken from user where username=? and password=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql,rowMapper, username, password)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        create(user);
        return findByUserToken(user.getUserToken());
    }
}
