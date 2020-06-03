package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRegistrationResponse extends BaseResponse {
    private Integer userToken;
}
