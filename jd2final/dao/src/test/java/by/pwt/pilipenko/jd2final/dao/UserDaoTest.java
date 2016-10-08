package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class UserDaoTest
        extends Assert {
    //private static User user;
    private static User user1;
    private static UserRole userRole1;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Before
    public void init() {
        User user = new User();

        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            user.setBirthDate(format.parse("10.01.1977"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setLogin("apilipenka");
        user.setPassword("123456");

        UserRole userRole = new UserRole();
        userRole.setName("Test");
        userRole.setName("Test user");


        userRole1 = userRoleDAO.insert(userRole);

        user.setUserRole(userRole1);

        user1 = userDAO.insert(user);


    }

    @After
    public void close() {

        userDAO.delete(user1);
        userRoleDAO.delete(userRole1);

    }

    @Test
    public void test1FindById() {


        User user2 = userDAO.findEntityById(user1.getId());
        assertEquals(user1, user2);

    }

    @Test
    public void test2FindByEntity() {

        List<User> userList1 = new ArrayList<>();
        userList1.add(user1);


        List<User> userList2 = userDAO.findEntityByEntity(user1);
        assertEquals(userList1, userList2);


    }

    @Test
    public void test6FindEntityByPK() {


        User user2 = userDAO.findEntityByPK(user1);
        assertEquals(user1, user2);


    }


    @Test
    public void test7Update() {


        user1.setLogin("TestUserTest");
        userDAO.update(user1);
        userDAO.flush();
        User user2 = userDAO.findEntityById(user1.getId());
        assertEquals(user1, user2);


    }


    @Test
    public void test8DeleteById() {


        userDAO.delete(user1.getId());
        userDAO.flush();
        User user2 = userDAO.findEntityById(user1.getId());

        assertNull(user2);


    }

    @Test
    public void test9DeleteByEntity() {


        userDAO.delete(user1);
        userDAO.flush();
        List<User> userList2 = userDAO.findEntityByEntity(user1);

        assertEquals(userList2.size(), 0);


    }


}
