package ru.example.sbertest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.UserResultApiRequestHandler;
import ru.example.sbertest.request.UserResultApiRequest;
import ru.example.sbertest.response.UserResultApiResponse;

/**
 * Контроллер для работы пользователям. Получение конкретным пользователем всю необходимую инфомацию о себе.
 * */

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserResultApiRequestHandler userResultApiRequestHandler;

    @PostMapping(value = "/getResults")
    public UserResultApiResponse getResults(@RequestBody UserResultApiRequest request) {
        return userResultApiRequestHandler.handlerProcess(request);
    }
}
