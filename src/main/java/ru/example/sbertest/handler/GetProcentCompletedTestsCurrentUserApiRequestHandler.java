package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.CommonCheck;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.ProcentCompletedTestsApiResponse;
import ru.example.sbertest.service.StatisticService;

@Service
public class GetProcentCompletedTestsCurrentUserApiRequestHandler extends
        AbstractHandler<BaseRequest, ProcentCompletedTestsApiResponse> {
    @Autowired
    private CommonCheck<BaseRequest> commonCheck;
    @Autowired
    private StatisticService statisticService;
    @Override
    protected ProcentCompletedTestsApiResponse handleRequest(BaseRequest request) {
        commonCheck.checkRequest(request);
        return statisticService.getProcentCompletedTestsCurrentUser(request);
    }

    @Override
    protected ProcentCompletedTestsApiResponse createResponse() {
        return new ProcentCompletedTestsApiResponse();
    }
}
