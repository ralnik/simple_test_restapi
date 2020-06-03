package ru.example.sbertest.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String question;
    private TypeQuestion type;
}
