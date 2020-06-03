package ru.example.sbertest.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.UserRegistrationRequest;

@Component
public class RegistrationApiRequestCheck extends CommonCheckRequest<UserRegistrationRequest> {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void additionalCheckRequest(UserRegistrationRequest request) {
        super.additionalCheckRequest(request);
        if (checkUserInDB(request.getUsername(), request.getPassword())) {
            throw new ApiException(ApiError.USER_ALREADY_EXIST);
        }
    }

    private boolean checkUserInDB(String username, String password) {
        User user = null;
        try {
            user = userDAO.findByUserNameAndPassword(username, password);
        } finally {
            return user != null;
        }
    }

    @Override
    public void checkAuthorizedUser(UserRegistrationRequest request) {
        //при регистрации userToken еще пустой, поэтому проверку не проводим
    }
}
