package ru.example.sbertest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.sbertest.request.AuthenticationApiRequest;
import ru.example.sbertest.response.AuthenticationApiResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends CommonControllerTest {

    @Before
    public void setUp() {
        super.setUp();
        super.registrtrationUser();
    }

    @Test
    public void authenticationTest() {
        AuthenticationApiRequest request = new AuthenticationApiRequest();
        request.setUsername(super.getUser().getUsername());
        request.setPassword(super.getUser().getPassword());

        ResponseEntity<AuthenticationApiResponse> response = super.restTemplate
                .postForEntity(super.getRootUrl() + "/authentication/",
                        request,
                        AuthenticationApiResponse.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(Integer.valueOf(super.getUser().hashCode()), response.getBody().getUserToken());
    }
}
