package ru.example.sbertest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.UsersTestCompleteApiResponse;
import ru.example.sbertest.service.StatisticService;

@Service
public class GetUserTestCompletedApiRequestHandler extends AbstractHandler<BaseRequest, UsersTestCompleteApiResponse> {
    @Autowired
    private StatisticService statisticService;

    @Override
    protected UsersTestCompleteApiResponse handleRequest(BaseRequest request) {
        UsersTestCompleteApiResponse response = createResponse();
        response.setUsers(statisticService.getUsersTestCompleted());
        return response;
    }

    @Override
    protected UsersTestCompleteApiResponse createResponse() {
        return new UsersTestCompleteApiResponse();
    }
}
