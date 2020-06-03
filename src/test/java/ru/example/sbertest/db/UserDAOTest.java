package ru.example.sbertest.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.sbertest.db.dao.user.UserDAO;
import ru.example.sbertest.db.entities.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class UserDAOTest extends CommonDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUp(){
        super.setUp();
        super.createNewUser();
    }

    @Test
    public void findByUserNameAndPasswordTest() {
        User user = userDAO.findByUserNameAndPassword(super.getUser().getUsername(),
                super.getUser().getPassword());
        assertEquals(super.getUser().getUsername(), user.getUsername());
        assertEquals(super.getUser().getPassword(), user.getPassword());
        assertEquals(super.getUser().getUserToken(),user.getUserToken());
    }

    @Test
    public void findByUserTokenTest() {
        User user = userDAO.findByUserToken(super.getUser().getUserToken());
        assertEquals(super.getUser().getUserToken(), user.getUserToken());
    }
}
