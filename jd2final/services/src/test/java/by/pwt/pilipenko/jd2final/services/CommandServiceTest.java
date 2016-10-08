package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Command;
import by.pwt.pilipenko.jd2final.services.interfaces.ICommandService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommandServiceTest
        extends Assert {

    private static Command command1;
    @Autowired
    private ICommandService commandService;

    @Test
    public void test1GetEntity() throws Exception {

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        command1 = commandService.insertEntity(command);


        Command command2 = commandService.getEntity(command1.getId());
        assertEquals(command1, command2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Command> commandList1 = new ArrayList<Command>();
        commandList1.add(command1);


        List<Command> commandList2 = commandService.searchEntityByName(command1.getCommand());
        assertEquals(commandList1, commandList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Command command2 = commandService.getEntityByPK(command1);
        assertEquals(command1, command2);


    }


    @Test
    public void test7Update() throws Exception {


        command1.setComment("Russian rubble test");
        commandService.updateEntity(command1);
        Command command2 = commandService.getEntity(command1.getId());

        assertEquals(command1, command2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        commandService.deleteEntity(command1.getId());


        Command command2 = commandService.getEntity(command1.getId());

        assertNull(command2);


    }


}
