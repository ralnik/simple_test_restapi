package ru.example.sbertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.AuthenticationApiRequestHandler;
import ru.example.sbertest.request.AuthenticationApiRequest;
import ru.example.sbertest.response.AuthenticationApiResponse;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationApiRequestHandler authenticationApiRequestHandler;

    @PostMapping(value = "/")
    public AuthenticationApiResponse authentication(@RequestBody AuthenticationApiRequest request) {
        return authenticationApiRequestHandler.handlerProcess(request);
    }
}
