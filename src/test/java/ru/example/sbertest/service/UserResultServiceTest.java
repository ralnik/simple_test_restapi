package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.request.UserResultApiRequest;
import ru.example.sbertest.response.UserResultApiResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResultServiceTest {
    @Mock
    private UserResultService userResultService;

    private UserResultApiResponse responseTest;

    @Before
    public void testSetup() {
        List<UserResultApiResponse.UserResult> userResultList = new ArrayList<>();
        UserResultApiResponse.UserResult userResult = new UserResultApiResponse.UserResult();
        userResult.setUser("testuser");
        userResult.setQuestion("question?");
        userResult.setAnswer("answer");
        userResult.setMistake(false);
        userResultList.add(userResult);

        responseTest = new UserResultApiResponse();
        responseTest.setResults(userResultList);

        when(userResultService.getUserResults(any())).thenReturn(responseTest);
    }

    @Test
    public void getUserResultsTest() {
        UserResultApiResponse response = userResultService.getUserResults(new UserResultApiRequest());

        UserResultApiResponse.UserResult userResult = response.getResults().get(0);

        assertNotNull(response);
        assertEquals("testuser", userResult.getUser());
        assertEquals("question?", userResult.getQuestion());
        assertEquals("answer", userResult.getAnswer());
    }
}
