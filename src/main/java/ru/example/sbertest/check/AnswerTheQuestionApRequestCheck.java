package ru.example.sbertest.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.sbertest.db.dao.resultuser.ResultUserDAO;
import ru.example.sbertest.db.entities.ResultUser;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.AnswerTheQuestionApiRequest;

import java.util.List;
import java.util.Optional;

@Component
public class AnswerTheQuestionApRequestCheck extends CommonCheckRequest<AnswerTheQuestionApiRequest> {
    @Autowired
    private ResultUserDAO resultUserDAO;

    @Override
    public void additionalCheckRequest(AnswerTheQuestionApiRequest request) {
        super.additionalCheckRequest(request);
        List<ResultUser> list = Optional.of(resultUserDAO.findByNameAndUserToken(
                request.getTestName(),
                request.getUserToken()))
                .get();
        if (list.size() > 0) {
            throw new ApiException(ApiError.TEST_ALREADY_DONE, "Test already done");
        }
    }
}
