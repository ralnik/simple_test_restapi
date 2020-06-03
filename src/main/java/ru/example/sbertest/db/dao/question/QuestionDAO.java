package ru.example.sbertest.db.dao.question;

import ru.example.sbertest.db.dao.DAO;
import ru.example.sbertest.db.entities.Question;

public interface QuestionDAO extends DAO<Question> {
    Question findByQuestionAndTypeId(String question, Long typeId);
    Question save(Question question);
}
