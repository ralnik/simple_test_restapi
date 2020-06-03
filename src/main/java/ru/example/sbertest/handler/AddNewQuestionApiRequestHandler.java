package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.CommonCheckRequest;
import ru.example.sbertest.request.AddNewQuestionApiRequest;
import ru.example.sbertest.response.AddNewQuestionApiResponse;
import ru.example.sbertest.service.AddNewQuestionService;

@Service
public class AddNewQuestionApiRequestHandler
        extends AbstractHandler<AddNewQuestionApiRequest, AddNewQuestionApiResponse> {
    @Autowired
    private CommonCheckRequest<AddNewQuestionApiRequest> commonCheckRequest;
    @Autowired
    private AddNewQuestionService addNewQuestionService;

    @Override
    protected AddNewQuestionApiResponse handleRequest(AddNewQuestionApiRequest request) {
        AddNewQuestionApiResponse response = createResponse();
        commonCheckRequest.checkRequest(request);
        addNewQuestionService.isAddNewQuestion(request);
        return response;
    }

    @Override
    protected AddNewQuestionApiResponse createResponse() {
        return new AddNewQuestionApiResponse();
    }
}
