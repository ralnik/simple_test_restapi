package ru.example.sbertest.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.BaseRequest;

import java.util.Optional;

@Component
public class CommonCheckRequest<T extends BaseRequest> implements CommonCheck<T> {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void checkRequest(T request) {
        checkAuthorizedUser(request);
        additionalCheckRequest(request);
    }

    public void additionalCheckRequest(T request) {
        //implemented by children
    }

    public void checkAuthorizedUser(T request) {
        if (request.getUserToken() != null) {
            Optional.ofNullable(userDAO.findByUserToken(request.getUserToken()))
                    .orElseThrow(() -> new ApiException(ApiError.AUTORIZED_ERROR));
        } else {
            throw new ApiException(ApiError.ERROR_REQUEST_FORMAT);
        }
    }
}
