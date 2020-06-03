package ru.example.sbertest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.sbertest.db.entities.User;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    private AuthenticationService authenticationService;

    private User userTest;

    @Before
    public void testSetup() {
        userTest = new User();
        userTest.setUserToken(12345678);
        userTest.setUsername("testuser");
        userTest.setPassword("password");

        when(authenticationService.getAuthentication(anyString(), anyString())).thenReturn(userTest);
    }

    @Test
    public void getAuthenticationTest() {
        User user = authenticationService.getAuthentication("testuser", "password");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
    }

}
