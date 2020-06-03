package ru.example.sbertest.exception;

import lombok.Getter;
import ru.example.sbertest.error.ApiError;

public class ApiException extends RuntimeException {
    @Getter
    private final Integer errorCode;

    public ApiException(ApiError error) {
        this.errorCode = error.getErrorCode();
    }

    public ApiException() {
        this(ApiError.INTERNAL_ERROR);
    }

    public ApiException(ApiError error, String errorMessage) {
        super(errorMessage);
        this.errorCode = error.getErrorCode();
    }

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
