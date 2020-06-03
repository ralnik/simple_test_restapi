package ru.example.sbertest.db.dao.answer;

import ru.example.sbertest.db.dao.DAO;
import ru.example.sbertest.db.entities.Answer;

public interface AnswerDAO extends DAO<Answer> {
    Answer findByQuestionId(Long id);
}
