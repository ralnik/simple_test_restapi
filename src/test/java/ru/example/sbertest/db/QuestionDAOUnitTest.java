package ru.example.sbertest.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ru.example.sbertest.db.dao.question.QuestionDAO;
import ru.example.sbertest.db.entities.Question;
import ru.example.sbertest.db.entities.TypeQuestion;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyLong;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionDAOUnitTest {
    @Mock
    private QuestionDAO questionDAO;

    private Question questionTest;

    @Before
    public void testSetup() {
        questionTest = new Question();
        questionTest.setId(1L);
        questionTest.setType(new TypeQuestion());
        questionTest.setQuestion("name?");

        when(questionDAO.findById(1L)).thenAnswer(new Answer() {

            public Question answer(InvocationOnMock invocation) {
                // we can take method arguments as array of objects
                Object[] args = invocation.getArguments();
                Question question = new Question();
                question.setId(1L);
                question.setType(new TypeQuestion());
                question.setQuestion("surname?");
                return question;
            }
        });


    }

    @Test
    public void findByIdTest() throws Exception {
        Question question = questionDAO.findById(1L);

        assertNotNull(question);
        assertNotEquals(questionTest, question);
        assertEquals(question.getId(), questionTest.getId());
        assertNotEquals(question.getQuestion(), questionTest.getQuestion());

        assertNull(questionDAO.findById(2L));
    }

}
