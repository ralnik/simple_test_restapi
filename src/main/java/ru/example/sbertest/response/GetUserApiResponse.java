package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetUserApiResponse extends BaseResponse {
    private List<String> users;
    private Integer countUsers;
}
