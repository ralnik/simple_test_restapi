package ru.example.sbertest.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class Test implements Serializable {
   private static final long serialVersionUID = 1L;

   private Long id;
   private List<Question> question;
   private String name;
}
