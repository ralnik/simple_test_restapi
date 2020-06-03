package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.RegistrationApiRequestCheck;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.request.UserRegistrationRequest;
import ru.example.sbertest.response.UserRegistrationResponse;
import ru.example.sbertest.service.UserRegistrationService;

@Service
public class UserRegistrationHandler extends AbstractHandler<UserRegistrationRequest, UserRegistrationResponse> {
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private RegistrationApiRequestCheck registrationApiRequestCheck;

    @Override
    protected UserRegistrationResponse handleRequest(UserRegistrationRequest request) {
        UserRegistrationResponse response = createResponse();
        registrationApiRequestCheck.checkRequest(request);

        User user = userRegistrationService.registrationUser(request);
        response.setUserToken(user.getUserToken());
        return response;
    }

    @Override
    protected UserRegistrationResponse createResponse() {
        return new UserRegistrationResponse();
    }
}
