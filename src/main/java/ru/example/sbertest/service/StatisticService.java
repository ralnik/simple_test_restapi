package ru.example.sbertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.db.dao.resultuser.ResultUserDAO;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.GetUserApiResponse;
import ru.example.sbertest.response.ProcentCompletedTestsApiResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ResultUserDAO resultUserDAO;

    public GetUserApiResponse getUsers() {
        GetUserApiResponse response = new GetUserApiResponse();
        List<String> userList = userDAO.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        response.setUsers(userList);
        response.setCountUsers(userList.size());
        return response;
    }

    public ProcentCompletedTestsApiResponse getProcentCompletedTests() {
        ProcentCompletedTestsApiResponse response = new ProcentCompletedTestsApiResponse();
        response.setResult(resultUserDAO.getUserTestDoneInProcent());
        return response;
    }

    public ProcentCompletedTestsApiResponse getProcentCompletedTestsCurrentUser(BaseRequest request) {
        ProcentCompletedTestsApiResponse response = new ProcentCompletedTestsApiResponse();
        response.setResult(resultUserDAO.getUserTestDoneInProcentByUserToken(request.getUserToken()));
        return response;
    }

    public List<String> getUsersTestCompleted() {
        return resultUserDAO.findUsersTestDone();
    }
}
