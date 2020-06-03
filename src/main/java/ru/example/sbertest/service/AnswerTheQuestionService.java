package ru.example.sbertest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.db.dao.answer.AnswerDAO;
import ru.example.sbertest.db.dao.resultuser.ResultUserDAO;
import ru.example.sbertest.db.dao.test.TestDAO;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.db.entities.ResultUser;
import ru.example.sbertest.db.entities.Test;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.AnswerTheQuestionApiRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnswerTheQuestionService {
    private static final Long SELECTED_ANSWER = 1L;
    private static final Long SELF_ANSWER = 2L;

    @Autowired
    private ResultUserDAO resultUserDAO;
    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TestDAO testDAO;

    public Map<Long, Boolean> getResult(AnswerTheQuestionApiRequest request) {
        Map<Long, Boolean> result = new HashMap<>();

        Test test = testDAO.findByName(request.getTestName());
        for (Question question : test.getQuestion()) {
            ResultUser resultUser = new ResultUser();
            AnswerTheQuestionApiRequest.Answered answered =
                    request.getAnswers()
                            .stream()
                            .filter(var -> var.getQuestion_id().equals(question.getId()))
                            .findFirst()
                            .get();

            Answer answer = answerDAO.findById(answered.getAnswer_id());
            User user = userDAO.findByUserToken(request.getUserToken());

            resultUser.setTest(test);
            resultUser.setUser(user);
            resultUser.setAnswer(answer);
            if (question.getType().getId().equals(SELECTED_ANSWER) && Boolean.TRUE.equals(answer.getIsCorrect())) {
                resultUser.setMistake(false);
            } else {
                resultUser.setMistake(true);
            }
            if (question.getType().getId().equals(SELF_ANSWER)) {
                resultUser.setSelfAnswer(answered.getSelfAnswer());
                if (answered.getSelfAnswer().equals(answer.getAnswer())) {
                    resultUser.setMistake(false);
                } else {
                    resultUser.setMistake(true);
                }
            }

            result.put(question.getId(), resultUser.getMistake());
            resultUserDAO.create(resultUser);
        }
        return result;
    }
}
