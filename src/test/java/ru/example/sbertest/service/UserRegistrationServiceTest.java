package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.UserRegistrationRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {
    @Mock
    private UserRegistrationService userRegistrationService;

    private User userTest;

    @Before
    public void testSetup() {
        userTest = new User();
        userTest.setUserToken(12345678);
        userTest.setUsername("testuser");
        userTest.setPassword("password");

        when(userRegistrationService.registrationUser(any())).thenReturn(userTest);
    }

    @Test
    public void registrationUserTest() {
        User user = userRegistrationService.registrationUser(new UserRegistrationRequest());

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
