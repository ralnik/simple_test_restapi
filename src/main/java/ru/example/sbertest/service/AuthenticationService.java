package ru.example.sbertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.User;

@Service
public class AuthenticationService {
    @Autowired
    private UserDAO userDAO;

    public User getAuthentication(String username, String password) {
        return userDAO.findByUserNameAndPassword(username, password);
    }
}
