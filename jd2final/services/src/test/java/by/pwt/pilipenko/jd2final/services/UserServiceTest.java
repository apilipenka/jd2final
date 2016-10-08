package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
        extends Assert {

    private static UserRole userRole1;
    private static User user1;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;

    @Before
    public void init() {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);

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

        user.setUserRole(userRole1);

        user1 = userService.insertEntity(user);

    }

    @After
    public void close() throws SQLException, ClassNotFoundException, NamingException {
        if (user1 != null) {
            User user = userService.getEntity(user1.getId());
            if (user != null)
                userService.deleteEntity(user.getId());
        }
        if (userRole1 != null) {
            UserRole userRole = userRoleService.getEntity(userRole1.getId());
            if (userRole != null)
                userRoleService.deleteEntity(userRole1.getId());
        }
    }

    @Test
    public void test1GetEntity() throws Exception {


        User user2 = userService.getEntity(user1.getId());
        assertEquals(user1, user2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<User> userList1 = new ArrayList<User>();
        userList1.add(user1);


        List<User> userList2 = userService.searchEntityByName(user1.getPersonalNumber());
        assertEquals(userList1, userList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        User user2 = userService.getEntityByPK(user1);
        assertEquals(user1, user2);


    }


    @Test
    public void test7Update() throws Exception {


        user1.setFirstName("Russian rubble test");
        userService.updateEntity(user1);
        User user2 = userService.getEntity(user1.getId());

        assertEquals(user1, user2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        userService.deleteEntity(user1.getId());


        User user2 = userService.getEntity(user1.getId());

        assertNull(user2);


    }


}
