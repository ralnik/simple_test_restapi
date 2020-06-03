package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.db.response.GetUserTestDoneInProcent;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.GetUserApiResponse;
import ru.example.sbertest.response.ProcentCompletedTestsApiResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceUnitTest {
    @Mock
    private StatisticService statisticService;

    private List<String> userListTest;
    private GetUserApiResponse getUserApiResponseTest;
    private ProcentCompletedTestsApiResponse procentCompletedTestsApiResponse;
    private BaseRequest baseRequest;

    @Before
    public void testSetup() {
        userListTest = new ArrayList<>();
        userListTest.add("testuser");

        getUserApiResponseTest = new GetUserApiResponse();
        getUserApiResponseTest.setCountUsers(1);
        getUserApiResponseTest.setUsers(userListTest);

        GetUserTestDoneInProcent getUserTestDoneInProcent = new GetUserTestDoneInProcent();
        getUserTestDoneInProcent.setUser(userListTest.get(0));
        getUserTestDoneInProcent.setTest("test 1");
        getUserTestDoneInProcent.setCountQuestion(5);
        getUserTestDoneInProcent.setCountMistake(0);
        getUserTestDoneInProcent.setProcent("100%");

        List<GetUserTestDoneInProcent> getUserTestDoneInProcentList = new ArrayList<>();
        getUserTestDoneInProcentList.add(getUserTestDoneInProcent);

        procentCompletedTestsApiResponse = new ProcentCompletedTestsApiResponse();
        procentCompletedTestsApiResponse.setResult(getUserTestDoneInProcentList);

        baseRequest = new BaseRequest();
        baseRequest.setUserToken(12345678);

        when(statisticService.getUsersTestCompleted()).thenReturn(userListTest);
        when(statisticService.getUsers()).thenReturn(getUserApiResponseTest);
        when(statisticService.getProcentCompletedTests()).thenReturn(procentCompletedTestsApiResponse);
        when(statisticService.getProcentCompletedTestsCurrentUser(baseRequest))
                .thenReturn(procentCompletedTestsApiResponse);
    }

    @Test
    public void getUsersTestCompleted() {
        List<String> users = statisticService.getUsersTestCompleted();

        assertNotNull(users);
        assertTrue(users.size() > 0);
        assertEquals("testuser", users.get(0));
    }

    @Test
    public void getUsersTest() {
        GetUserApiResponse response = statisticService.getUsers();

        assertNotNull(response);
        assertEquals(getUserApiResponseTest.getCountUsers(), response.getCountUsers());
        assertEquals(getUserApiResponseTest.getUsers().size(), response.getUsers().size());
    }

    @Test
    public void getProcentCompletedTests_Test() {
        ProcentCompletedTestsApiResponse response = statisticService.getProcentCompletedTests();

        assertNotNull(response);
        assertEquals(procentCompletedTestsApiResponse.getResult().size(), response.getResult().size());
    }

    @Test
    public void getProcentCompletedTestsCurrentUserTest() {
        ProcentCompletedTestsApiResponse response = statisticService.getProcentCompletedTestsCurrentUser(baseRequest);

        assertNotNull(response);
        assertEquals("testuser", response.getResult().get(0).getUser());
    }
}
