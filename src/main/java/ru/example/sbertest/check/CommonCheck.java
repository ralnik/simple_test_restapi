package ru.example.sbertest.check;

import ru.example.sbertest.request.BaseRequest;

public interface CommonCheck<T extends BaseRequest> {
    void checkRequest(T request);
}
