package ru.example.sbertest.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.sbertest.db.dao.typequestion.TypeQuestionDAOImpl;
import ru.example.sbertest.db.entities.TypeQuestion;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class TypeQuestionDAOTest extends CommonDAOTest {
    @Autowired
    private TypeQuestionDAOImpl typeQuestionDAO;

    @Before
    public void setUp(){
        super.setUpTypeQuestion();
        typeQuestionDAO.create(super.getTypeQuestion());
    }

    @Test
    public void findAllTest() {
        TypeQuestion type = typeQuestionDAO.findAll()
                .stream()
                .filter(typeQuestion -> typeQuestion.getName().equals(super.getTypeQuestion().getName()))
                .findFirst()
                .get();
        assertNotNull(type);
        assertEquals(super.getTypeQuestion().getName(), type.getName());
    }
}
