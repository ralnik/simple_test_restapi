package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.AuthenticationApiRequestCheck;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.AuthenticationApiRequest;
import ru.example.sbertest.response.AuthenticationApiResponse;
import ru.example.sbertest.service.AuthenticationService;

@Service
public class AuthenticationApiRequestHandler extends
        AbstractHandler<AuthenticationApiRequest, AuthenticationApiResponse> {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthenticationApiRequestCheck authenticationApiRequestCheck;

    @Override
    protected AuthenticationApiResponse handleRequest(AuthenticationApiRequest request) {
        AuthenticationApiResponse response = createResponse();

        authenticationApiRequestCheck.checkRequest(request);

        User user = authenticationService.getAuthentication(request.getUsername(), request.getPassword());
        response.setUserToken(user.getUserToken());
        return response;
    }

    @Override
    protected AuthenticationApiResponse createResponse() {
        return new AuthenticationApiResponse();
    }
}
