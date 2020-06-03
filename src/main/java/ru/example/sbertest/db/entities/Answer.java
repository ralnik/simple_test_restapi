package ru.example.sbertest.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String answer;
    private Question question;
    private Boolean isCorrect;
}
