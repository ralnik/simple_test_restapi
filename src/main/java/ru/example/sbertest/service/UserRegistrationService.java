package ru.example.sbertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.UserRegistrationRequest;

@Service
public class UserRegistrationService {
    @Autowired
    private UserDAO userDAOImpl;

    public User registrationUser(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setUserToken(user.hashCode());
        return userDAOImpl.save(user);
    }
}
