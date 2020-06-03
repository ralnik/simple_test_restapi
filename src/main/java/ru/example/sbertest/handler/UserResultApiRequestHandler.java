package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.CommonCheck;
import ru.example.sbertest.request.UserResultApiRequest;
import ru.example.sbertest.response.UserResultApiResponse;
import ru.example.sbertest.service.UserResultService;

@Service
public class UserResultApiRequestHandler extends
        AbstractHandler<UserResultApiRequest, UserResultApiResponse> {
    @Autowired
    private CommonCheck<UserResultApiRequest> commonCheck;
    @Autowired
    private UserResultService userResultService;

    @Override
    protected UserResultApiResponse handleRequest(UserResultApiRequest request) {
        UserResultApiResponse response;
        commonCheck.checkRequest(request);
        response = userResultService.getUserResults(request);
        return response;
    }

    @Override
    protected UserResultApiResponse createResponse() {
        return new UserResultApiResponse();
    }
}
