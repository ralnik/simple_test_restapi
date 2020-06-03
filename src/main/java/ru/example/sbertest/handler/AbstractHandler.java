package ru.example.sbertest.handler;

import lombok.extern.slf4j.Slf4j;
import ru.example.sbertest.error.ApiError;
import ru.example.sbertest.exception.ApiException;
import ru.example.sbertest.request.BaseRequest;
import ru.example.sbertest.response.BaseResponse;

@Slf4j
public abstract class AbstractHandler<T extends BaseRequest, R extends BaseResponse> {

    public R handlerProcess(T request) {
        R response;
        try {
            response = handleRequest(request);
            response.setError(ApiError.SUCCESS.getErrorCode());
        } catch (ApiException e){
            log.debug("Handling request error; error code: {}", e.getErrorCode());
            response = createResponse();
            response.setError(e.getErrorCode());
            response.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Unknown error while handling request", e);
            response = createResponse();
            response.setError(ApiError.INTERNAL_ERROR.getErrorCode());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    protected abstract R handleRequest(T request);
    protected abstract R createResponse();
}
