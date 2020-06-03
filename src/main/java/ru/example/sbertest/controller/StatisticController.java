package ru.example.sbertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.GetProcentCompletedTestsApiRequestHandler;
import ru.example.sbertest.handler.GetProcentCompletedTestsCurrentUserApiRequestHandler;
import ru.example.sbertest.handler.GetUserTestCompletedApiRequestHandler;
import ru.example.sbertest.handler.GetUsersApiRequestHandler;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.GetUserApiResponse;
import ru.example.sbertest.response.ProcentCompletedTestsApiResponse;
import ru.example.sbertest.response.UsersTestCompleteApiResponse;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {
    @Autowired
    private GetUsersApiRequestHandler getUsersApiRequestHandler;
    @Autowired
    private GetProcentCompletedTestsApiRequestHandler getProcentCompletedTestsApiRequestHandler;
    @Autowired
    private GetProcentCompletedTestsCurrentUserApiRequestHandler getProcentCompletedTestsCurrentUserApiRequestHandler;
    @Autowired
    private GetUserTestCompletedApiRequestHandler getUserTestCompletedApiRequestHandler;

    @PostMapping(value = "/getUsers")
    public GetUserApiResponse getUsers(@RequestBody BaseRequest request){
        return getUsersApiRequestHandler.handlerProcess(request);
    }

    @PostMapping(value = "/procentCompletedTests")
    public ProcentCompletedTestsApiResponse getProcentCompletedTests(@RequestBody BaseRequest request) {
        return getProcentCompletedTestsApiRequestHandler.handlerProcess(request);
    }

    @PostMapping(value = "/procentCompletedTestsCurrentUser")
    public ProcentCompletedTestsApiResponse getProcentCompletedTestsCurrentUser(@RequestBody BaseRequest request) {
        return getProcentCompletedTestsCurrentUserApiRequestHandler.handlerProcess(request);
    }

    @PostMapping(value = "/usersTestComleted")
    public UsersTestCompleteApiResponse getUsersTestCompleted(@RequestBody BaseRequest request) {
        return getUserTestCompletedApiRequestHandler.handlerProcess(request);
    }
}
