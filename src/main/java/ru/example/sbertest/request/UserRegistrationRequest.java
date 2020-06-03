package ru.example.sbertest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRegistrationRequest extends BaseRequest {
    private String username;
    private String password;
}
