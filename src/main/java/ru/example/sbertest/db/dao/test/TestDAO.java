package ru.example.sbertest.db.dao.test;

import ru.example.sbertest.db.dao.DAO;
import ru.example.sbertest.db.entities.Test;

import java.util.List;

public interface TestDAO extends DAO<Test> {
    Test findByName(String name);
}
