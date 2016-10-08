package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Command;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class UserRoleDaoTest
        extends Assert {
    private static UserRole userRole1;
    private static Command command1;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private CommandDAO commandDAO;

    @Before
    public void init() {
        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");


        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");


        command1 = commandDAO.insert(command);
        userRole.addCommand(command1);
        userRole1 = userRoleDAO.insert(userRole);
        userRoleDAO.flush();


    }

    @After
    public void close() {
        commandDAO.delete(command1);
        userRoleDAO.delete(userRole1);

    }


    @Test
    public void test1FindById() {


        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());
        assertEquals(userRole1, userRole2);


    }

    @Test
    public void test2FindByEntity() {

        List<UserRole> userRoleList1 = new ArrayList<>();
        userRoleList1.add(userRole1);


        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole1);
        assertEquals(userRoleList1, userRoleList2);


    }

    @Test
    public void test6FindEntityByPK() {


        UserRole userRole2 = userRoleDAO.findEntityByPK(userRole1);
        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test7Update() {


        userRole1.setDescription("Test role for update");

        userRoleDAO.update(userRole1);
        userRoleDAO.flush();
        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test8DeleteById() {

        userRoleDAO.delete(userRole1.getId());
        userRoleDAO.flush();
        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

        assertNull(userRole2);


    }

    @Test
    public void test9DeleteByEntity() {


        userRoleDAO.delete(userRole1);
        userRoleDAO.flush();

        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole1);

        assertEquals(userRoleList2.size(), 0);


    }


}
