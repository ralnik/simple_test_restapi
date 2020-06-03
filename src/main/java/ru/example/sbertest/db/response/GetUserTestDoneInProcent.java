package ru.example.sbertest.db.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class GetUserTestDoneInProcent {
    private String user;
    private String test;
    private Integer countQuestion;
    private Integer countMistake;
    private String procent;
}
