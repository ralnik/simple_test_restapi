package ru.example.sbertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.db.dao.resultuser.ResultUserDAO;
import ru.example.sbertest.db.entities.ResultUser;
import ru.example.sbertest.request.UserResultApiRequest;
import ru.example.sbertest.response.UserResultApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserResultService {
    @Autowired
    private ResultUserDAO resultUserDAO;

    public UserResultApiResponse getUserResults(UserResultApiRequest request) {
        UserResultApiResponse response = new UserResultApiResponse();
        List<ResultUser> listResultUser = resultUserDAO.findByUserToken(request.getUserToken());
        List<UserResultApiResponse.UserResult> entry = new ArrayList<>();

        for (ResultUser resultUser : listResultUser) {
            UserResultApiResponse.UserResult result = new UserResultApiResponse.UserResult();
            result.setUser(resultUser.getUser().getUsername());
            result.setAnswer(resultUser.getAnswer().getAnswer());
            result.setMistake(resultUser.getMistake());
            entry.add(result);
        }

        response.setResults(entry);
        return response;
    }
}
