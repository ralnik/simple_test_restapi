package ru.example.sbertest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.sbertest.configuration.BaseConnectionTest;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.UserRegistrationRequest;
import ru.example.sbertest.response.UserRegistrationResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class RegistrationControllerTest extends BaseConnectionTest {

    private UserRegistrationRequest getUserRegistrationRequest(User user) {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());
        return request;
    }

    @Test
    public void userRegistrationTest() {
        String username = "user" + System.currentTimeMillis() + "@gmail.com";
        User user = new User();
        user.setUsername(username);
        user.setPassword("user123");
        ResponseEntity<UserRegistrationResponse> response = super.restTemplate
                .postForEntity(super.getRootUrl() + "/registration/newuser",
                        getUserRegistrationRequest(user),
                        UserRegistrationResponse.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(Integer.valueOf(user.hashCode()), response.getBody().getUserToken());
    }
}
