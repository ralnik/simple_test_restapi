package ru.example.sbertest.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.AuthenticationApiRequest;

import java.util.Optional;

@Component
@Slf4j
public class AuthenticationApiRequestCheck extends CommonCheckRequest<AuthenticationApiRequest> {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void additionalCheckRequest(AuthenticationApiRequest request) {
        super.additionalCheckRequest(request);
    }

    @Override
    public void checkAuthorizedUser(AuthenticationApiRequest request) {
        //при авторизации userToken еще пустой, поэтому проверку проведем по логину и паролю
        Optional.ofNullable(userDAO.findByUserNameAndPassword(request.getUsername(), request.getPassword()))
                .orElseThrow(() -> new ApiException(ApiError.USER_NOT_EXIST));
    }
}
