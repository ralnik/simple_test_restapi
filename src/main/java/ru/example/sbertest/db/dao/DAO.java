package ru.example.sbertest.db.dao;

import java.util.List;

public interface DAO<T> {
    List<T> findAll();
    T findById(Long id);
    void create(T object);
    void update(T object);
    void delete(T object);
}
