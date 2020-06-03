package ru.example.sbertest.db;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.sbertest.Application;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.TypeQuestion;
import ru.example.sbertest.db.entities.User;

@SpringBootTest(classes = Application.class)
public class CommonDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Getter
    private User user;
    @Getter
    private TypeQuestion typeQuestion;

    public User getUser() {
        return user;
    }

    protected void setUp() {
        User user = new User();
        user.setUsername("user"+System.currentTimeMillis()+"@gmail.com");
        user.setPassword("password");
        user.setUserToken(user.hashCode());
        this.user = user;
    }

    protected void createNewUser(){
        if (this.user != null) {
            userDAO.create(this.user);
        }
    }

    protected void setUpTypeQuestion() {
        TypeQuestion typeQuestion = new TypeQuestion();
        typeQuestion.setName("random question " + System.currentTimeMillis());
        this.typeQuestion = typeQuestion;
    }
}
