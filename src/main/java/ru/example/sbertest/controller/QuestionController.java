package ru.example.sbertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.AddNewQuestionApiRequestHandler;
import ru.example.sbertest.request.AddNewQuestionApiRequest;
import ru.example.sbertest.response.AddNewQuestionApiResponse;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private AddNewQuestionApiRequestHandler addNewQuestionApiRequestHandler;

    @PostMapping(value = "/addNew")
    public AddNewQuestionApiResponse addNewQuestion(@RequestBody AddNewQuestionApiRequest request) {
        return addNewQuestionApiRequestHandler.handlerProcess(request);
    }
}
