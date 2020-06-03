package ru.example.sbertest.response;

import lombok.Data;

@Data
public class BaseResponse {
    private Integer error;
    private String errorMessage;
}
