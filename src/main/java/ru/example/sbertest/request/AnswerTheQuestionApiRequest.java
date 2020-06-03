package ru.example.sbertest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnswerTheQuestionApiRequest extends BaseRequest {
    private String testName;
    private List<AnswerTheQuestionApiRequest.Answered> answers;

    @Data
    public static class Answered {
        private Long question_id;
        private Long answer_id;
        private String selfAnswer;
    }
}
