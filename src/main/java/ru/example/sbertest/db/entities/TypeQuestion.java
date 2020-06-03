package ru.example.sbertest.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class TypeQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id = 1L;
    private String name;
}
