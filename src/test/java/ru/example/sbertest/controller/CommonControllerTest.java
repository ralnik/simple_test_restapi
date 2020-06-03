package ru.example.sbertest.controller;

import org.springframework.http.ResponseEntity;
import ru.example.sbertest.configuration.BaseConnectionTest;
import ru.example.sbertest.db.entities.User;
import ru.example.sbertest.request.AuthenticationApiRequest;
import ru.example.sbertest.request.UserRegistrationRequest;
import ru.example.sbertest.response.AuthenticationApiResponse;
import ru.example.sbertest.response.UserRegistrationResponse;

public class CommonControllerTest extends BaseConnectionTest {
    private User user;

    protected void setUp() {
        User user = new User();
        user.setUsername("user"+System.currentTimeMillis()+"@gmail.com");
        user.setPassword("password");
        this.user = user;
    }

    public void registrtrationUser(){
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());

        super.restTemplate.postForEntity(super.getRootUrl() + "/registration/newuser",
                        request,
                        UserRegistrationResponse.class);
    }

    public void autentication() {
        AuthenticationApiRequest request = new AuthenticationApiRequest();
        request.setUsername(user.getUsername());
        request.setPassword(user.getPassword());

        ResponseEntity<AuthenticationApiResponse> response = super.restTemplate
                .postForEntity(super.getRootUrl() + "/authentication/",
                        request,
                        AuthenticationApiResponse.class);
        user.setUserToken(response.getBody().getUserToken());
    }
    public User getUser() {
        return user;
    }
}
