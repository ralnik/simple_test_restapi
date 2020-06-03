package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.request.AddNewQuestionApiRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddNewQuestionServiceTest {
    @Mock
    private AddNewQuestionService addNewQuestionService;

    private AddNewQuestionApiRequest addNewQuestionApiRequestTest;

    @Before
    public void testSetup() {
        List<Answer> answerListTest = new ArrayList<>();
        answerListTest.add(new Answer());

        addNewQuestionApiRequestTest = new AddNewQuestionApiRequest();
        addNewQuestionApiRequestTest.setAnswers(answerListTest);
        addNewQuestionApiRequestTest.setType_id(1);
        addNewQuestionApiRequestTest.setQuestion("question?");

        when(addNewQuestionService.isAddNewQuestion(addNewQuestionApiRequestTest)).thenReturn(true);
    }

    @Test
    public void isAddNewQuestionTest() {
        Boolean result = addNewQuestionService.isAddNewQuestion(addNewQuestionApiRequestTest);
        assertNotNull(result);
        assertTrue(result);
    }
}
