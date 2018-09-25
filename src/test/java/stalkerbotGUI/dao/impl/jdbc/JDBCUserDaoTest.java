package stalkerbotGUI.dao.impl.jdbc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import stalkerbotGUI.dao.DaoFactory;
import stalkerbotGUI.dao.UserDao;
import stalkerbotGUI.model.entity.User;
import stalkerbotGUI.model.entity.enums.RoleType;

import java.util.Optional;

//TODO commit-rollback
//TODO test service with Mockito
public class JDBCUserDaoTest {

    private final String USER_EMAIL = "test_user@ukr.net";

    private User user = new User.Builder()
        .setName("test_user")
        .setSurname("")
        .setRole(RoleType.USER)
        .setEmail(USER_EMAIL)
        .getInstance();

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private UserDao userDao;

    @Test
    public void createAndDelete() {
        userDao = daoFactory.createUserDao();

        userDao.create(user);

        User user1 = userDao.getUserByEmail(USER_EMAIL).get();

        User user2 = userDao.findById(user1.getId()).get();

        assertEquals(user1, user2);

        userDao.delete(user1.getId());

        Optional<User> user3 = userDao.getUserByEmail(USER_EMAIL);

        if (user3.isPresent()) fail();

        userDao.close();
    }
}