package ru.example.sbertest.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum  ApiError {
    ERROR(-1),
    SUCCESS(0),
    INTERNAL_ERROR(10),
    ERROR_REQUEST_FORMAT(11),
    USER_ALREADY_EXIST(12),
    USER_NOT_EXIST(13),
    AUTORIZED_ERROR(14),
    TEST_ALREADY_DONE(15);

    private int errorCode;

    public static ApiError getByCode(Integer errorCode) {
        return Stream.of(values())
                .filter(e -> errorCode.equals(e.errorCode))
                .findAny()
                .orElse(null);
    }
}
