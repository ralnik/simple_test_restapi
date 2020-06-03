package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.check.CommonCheck;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.GetUserApiResponse;
import ru.example.sbertest.service.StatisticService;

@Service
public class GetUsersApiRequestHandler extends AbstractHandler<BaseRequest, GetUserApiResponse> {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private CommonCheck<BaseRequest> commonCheck;

    @Override
    protected GetUserApiResponse handleRequest(BaseRequest request) {
        commonCheck.checkRequest(request);
        GetUserApiResponse response = statisticService.getUsers();
        return response;
    }

    @Override
    protected GetUserApiResponse createResponse() {
        return new GetUserApiResponse();
    }
}
