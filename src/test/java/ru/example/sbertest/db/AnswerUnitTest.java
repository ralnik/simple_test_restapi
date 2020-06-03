package ru.example.sbertest.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.db.dao.answer.AnswerDAO;
import ru.example.sbertest.db.entities.Answer;
import ru.example.sbertest.db.entities.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerUnitTest {
    @Mock
    private AnswerDAO answerDAO;

    private Answer answerTest;
    private List<Answer> answerListTest;

    @Before
    public void onTestSetup() {
        answerTest = new Answer();
        answerTest.setAnswer("test?");
        answerTest.setQuestion(new Question());
        answerTest.setIsCorrect(true);
        answerTest.setId(1L);

        answerListTest = new ArrayList<>();
        answerListTest.add(answerTest);

        when(answerDAO.findById(anyLong())).thenReturn(answerTest);
        when(answerDAO.findAll()).thenReturn(answerListTest);
    }

    @Test
    public void findByIdTest() {
        Answer answer = answerDAO.findById(anyLong());

        assertNotNull(answer);
        assertEquals(answerTest, answer);
        assertTrue(answerTest.getId().equals(answer.getId()));
        assertSame(answerTest.getId(), answer.getId());
        assertSame(answerTest.getAnswer(), answer.getAnswer());
    }

    @Test
    public void findAllTest() {
        List<Answer> answerList = answerDAO.findAll();

        assertNotNull(answerList);
        assertTrue(answerList.size() == answerListTest.size());
        assertEquals(answerList.get(0).getAnswer(), answerListTest.get(0).getAnswer());
    }

}
