package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Command;
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
public class CommandDaoTest
        extends Assert {

    private static Command command1;
    //private static DAOFactory factory;
    @Autowired
    private CommandDAO commandDAO;

    @Before
    public void init() {
        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");


        command1 = commandDAO.insert(command);

    }

    @After
    public void close() {

        commandDAO.delete(command1);

    }

    @Test
    public void test1FindById() {


        Command command2 = commandDAO.findEntityById(command1.getId());
        assertEquals(command1, command2);


    }

    @Test
    public void test2FindByEntity() {

        List<Command> commandList1 = new ArrayList<>();
        commandList1.add(command1);


        List<Command> commandList2 = commandDAO.findEntityByEntity(command1);
        assertEquals(commandList1, commandList2);


    }

    @Test
    public void test6FindEntityByPK() {


        Command command2 = commandDAO.findEntityByPK(command1);
        assertEquals(command1, command2);


    }


    @Test
    public void test7Update() {


        command1.setComment("Test role for update");
        commandDAO.update(command1);
        Command command2 = commandDAO.findEntityById(command1.getId());

        assertEquals(command1, command2);


    }


    @Test
    public void test8DeleteById() {

        commandDAO.delete(command1.getId());

        Command command2 = commandDAO.findEntityById(command1.getId());

        assertNull(command2);


    }

    @Test
    public void test9DeleteByEntity() {


        commandDAO.delete(command1);
        commandDAO.flush();

        List<Command> commandList2 = commandDAO.findEntityByEntity(command1);

        assertEquals(commandList2.size(), 0);


    }


}
