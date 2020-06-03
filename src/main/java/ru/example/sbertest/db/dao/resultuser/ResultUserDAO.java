package ru.example.sbertest.db.dao.resultuser;

import ru.example.sbertest.db.dao.DAO;
import ru.example.sbertest.db.entities.ResultUser;
import ru.example.sbertest.db.entities.Test;
import ru.example.sbertest.db.response.GetUserTestDoneInProcent;

import java.util.List;

public interface ResultUserDAO extends DAO<ResultUser> {
    List<ResultUser> findByUserToken(Integer userToken);
    List<ResultUser>  findByNameAndUserToken(String name, Integer userToken);
    //выводит всех пользователей, кто прошел тестирование
    List<String> findUsersTestDone();
    List<GetUserTestDoneInProcent> getUserTestDoneInProcent();
    List<GetUserTestDoneInProcent> getUserTestDoneInProcentByUserToken(Integer userToken);
}
