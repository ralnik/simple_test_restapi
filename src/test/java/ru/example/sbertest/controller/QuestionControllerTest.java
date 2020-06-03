package ru.example.sbertest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.sbertest.db.dao.question.QuestionDAOImpl;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.request.AddNewQuestionApiRequest;
import ru.example.sbertest.response.AddNewQuestionApiResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class QuestionControllerTest extends CommonControllerTest {
    @Autowired
    private QuestionDAOImpl questionDAO;
    @Before
    public void setUp() {
        super.setUp();
        super.registrtrationUser();
        super.autentication();
    }

    @Test
    public void AddNewQuestionTest() {
        Answer answer = new Answer();
        answer.setAnswer("VasYA");
        answer.setIsCorrect(true);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);

        AddNewQuestionApiRequest request = new AddNewQuestionApiRequest();
        request.setUserToken(getUser().getUserToken());
        request.setQuestion("what is your name?");
        request.setType_id(2);
        request.setAnswers(answers);

        ResponseEntity<AddNewQuestionApiResponse> response = super.restTemplate
                .postForEntity(super.getRootUrl() + "/question/addNew",
                        request,
                        AddNewQuestionApiResponse.class);

        Question question = findQuestion(request);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(request.getQuestion(), question.getQuestion());
    }

    private Question findQuestion(AddNewQuestionApiRequest request) {
        return  questionDAO.findByQuestionAndTypeId(request.getQuestion(), Long.valueOf(request.getType_id()));
    }
}
