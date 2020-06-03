package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersTestCompleteApiResponse extends BaseResponse {
    List<String> users;
}
