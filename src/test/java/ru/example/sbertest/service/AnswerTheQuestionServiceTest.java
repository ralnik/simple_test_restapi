package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.request.AnswerTheQuestionApiRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerTheQuestionServiceTest {
    @Mock
    private AnswerTheQuestionService answerTheQuestionService;

    private Map<Long, Boolean> mapTest;

    @Before
    public void testSetup() {
        mapTest = new HashMap<>();
        mapTest.put(1L, true);
        mapTest.put(2L, false);

        when(answerTheQuestionService.getResult(any())).thenReturn(mapTest);
    }

    @Test
    public void getResultTest() {
        Map<Long, Boolean> map = answerTheQuestionService.getResult(new AnswerTheQuestionApiRequest());

        assertNotNull(map);
        assertTrue(map.get(1L));
        assertFalse(map.get(2L));
    }
}
