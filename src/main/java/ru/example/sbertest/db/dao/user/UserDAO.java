package ru.example.sbertest.db.dao.user;

import ru.example.sbertest.db.dao.DAO;
import ru.example.sbertest.db.entities.User;

public interface UserDAO extends DAO<User> {
    User findByUserToken(Integer userToken);
    User findByUserNameAndPassword(String username, String password);
    User save(User user);
}
