package ru.example.sbertest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.example.sbertest.db.entities.Answer;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddNewQuestionApiRequest extends BaseRequest {
    private String question;
    private Integer type_id;
    private List<Answer> answers;
 }
