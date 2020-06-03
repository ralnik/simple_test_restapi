package ru.example.sbertest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.sbertest.db.dao.answer.AnswerDAO;
import ru.example.sbertest.db.dao.answer.AnswerDAOImpl;
import ru.example.sbertest.db.dao.question.QuestionDAO;
import ru.example.sbertest.db.dao.question.QuestionDAOImpl;
import ru.example.sbertest.db.dao.typequestion.TypeQuestionDAO;
import ru.example.sbertest.db.dao.typequestion.TypeQuestionDAOImpl;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.db.entities.TypeQuestion;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.AddNewQuestionApiRequest;

import java.util.Optional;

@Slf4j
@Service
public class AddNewQuestionService {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private TypeQuestionDAO typeQuestionDAO;
    @Autowired
    private AnswerDAO answerDAO;

    @Transactional
    public boolean isAddNewQuestion(AddNewQuestionApiRequest request) {
        TypeQuestion typeQuestion = typeQuestionDAO.findById(Long.valueOf(request.getType_id()));

        Question question = new Question();
        question.setQuestion(request.getQuestion());
        question.setType(typeQuestion);

        try {
            question = questionDAO.save(question);

            for (Answer answer : request.getAnswers()) {
                Answer newAnswer = answer;
                newAnswer.setQuestion(question);
                Optional.ofNullable(answer.getIsCorrect())
                        .ifPresent(aBoolean -> newAnswer.setIsCorrect(aBoolean));
                answerDAO.create(answer);
            }
        } catch (ApiException e) {
            log.error("error due adding new question");
            return false;
        }
        return true;
    }
}
