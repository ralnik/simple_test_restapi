package ru.example.sbertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.sbertest.handler.AnswerTheQuestionApiRequestHandler;
import ru.example.sbertest.request.AnswerTheQuestionApiRequest;
import ru.example.sbertest.response.AnswerTheQuestionApiResponse;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {
    @Autowired
    private AnswerTheQuestionApiRequestHandler answerTheQuestionApiRequestHandler;

    @RequestMapping(value = "/currentQuestion")
    public AnswerTheQuestionApiResponse answerTheQuestion(@RequestBody AnswerTheQuestionApiRequest request) {
        return answerTheQuestionApiRequestHandler.handlerProcess(request);
    }
}
