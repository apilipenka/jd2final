package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import org.junit.*;
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
public class UserRoleServiceTest
        extends Assert {
    private static UserRole userRole1;
    @Autowired
    private IUserRoleService userRoleService;

    @Before
    public void init() throws NamingException, ClassNotFoundException, SQLException {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);

    }

    @After
    public void close() throws SQLException {
        if (userRole1 != null) {
            UserRole userRole = userRoleService.getEntity(userRole1.getId());
            if (userRole != null)
                userRoleService.deleteEntity(userRole1.getId());
        }

    }

    @Test
    public void test1GetEntity() throws Exception {


        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());
        assertEquals(userRole1, userRole2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<UserRole> userRoleList1 = new ArrayList<UserRole>();
        userRoleList1.add(userRole1);


        List<UserRole> userRoleList2 = userRoleService.searchEntityByName(userRole1.getName());
        assertEquals(userRoleList1, userRoleList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        UserRole userRole2 = userRoleService.getEntityByPK(userRole1);
        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test7Update() throws Exception {


        userRole1.setDescription("Russian rubble test");
        userRoleService.updateEntity(userRole1);
        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());

        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        userRoleService.deleteEntity(userRole1.getId());


        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());

        assertNull(userRole2);


    }


}
