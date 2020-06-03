package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.AnswerTheQuestionApRequestCheck;
import ru.example.sbertest.request.AnswerTheQuestionApiRequest;
import ru.example.sbertest.response.AnswerTheQuestionApiResponse;
import ru.example.sbertest.service.AnswerTheQuestionService;

@Service
public class AnswerTheQuestionApiRequestHandler extends
        AbstractHandler<AnswerTheQuestionApiRequest, AnswerTheQuestionApiResponse> {
    @Autowired
    private AnswerTheQuestionApRequestCheck answerTheQuestionApRequestCheck;
    @Autowired
    private AnswerTheQuestionService answerTheQuestionService;


    @Override
    protected AnswerTheQuestionApiResponse handleRequest(AnswerTheQuestionApiRequest request) {
        AnswerTheQuestionApiResponse response = createResponse();
        answerTheQuestionApRequestCheck.checkRequest(request);
        response.setResult(answerTheQuestionService.getResult(request));
        return response;
    }

    @Override
    protected AnswerTheQuestionApiResponse createResponse() {
        return new AnswerTheQuestionApiResponse();
    }
}
