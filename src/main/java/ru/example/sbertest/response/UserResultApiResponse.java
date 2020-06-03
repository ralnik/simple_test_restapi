package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserResultApiResponse extends BaseResponse {
    private List<UserResultApiResponse.UserResult> results;

    @Data
    public static class UserResult {
        private String user;
        private String question;
        private String answer;
        private boolean mistake;
    }
}
