package ru.example.sbertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.UserRegistrationHandler;
import ru.example.sbertest.request.UserRegistrationRequest;
import ru.example.sbertest.response.UserRegistrationResponse;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    private UserRegistrationHandler userRegistrationHandler;

    @PostMapping(value = "/newuser")
    public UserRegistrationResponse userRegistration(@RequestBody UserRegistrationRequest request) {
        return userRegistrationHandler.handlerProcess(request);
    }
}
