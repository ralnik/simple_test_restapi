package ru.example.sbertest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnswerTheQuestionApiResponse extends BaseResponse {
    // результат в виде map <номер вопроса, ошибка>
    private Map<Long, Boolean> result;
}
