package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.services.interfaces.IAgreementService;
import by.pwt.pilipenko.jd2final.services.interfaces.IBankService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class AgreementServiceTest
        extends Assert {

    private static Bank bank1;
    private static User user1;
    private static Agreement agreement1;
    private static UserRole userRole1;
    @Autowired
    private IBankService bankService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAgreementService agreementService;
    @Autowired
    private IUserRoleService userRoleService;

    @Before
    public void init() {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("1977779911");
        bank1 = bankService.insertEntity(bank);

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


        Agreement agreement = new Agreement();
        agreement.setNumber("19777791");

        try {
            agreement.setValidFromDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible
        }
        try {
            agreement.setValidToDate(format.parse("10.01.1917"));
        } catch (ParseException e) {
            //it is not possible
        }
        agreement.setBank(bank1);
        agreement.setClient(user1);


        agreement1 = agreementService.insertEntity(agreement);
    }

    @After
    public void close() {
        Bank bank = bankService.getEntityByPK(bank1);
        bank.deleteAgreement(agreement1);

        bankService.updateEntity(bank);
        User user = userService.getEntityByPK(user1);

        userService.deleteEntity(user.getId());
        bankService.deleteEntity(bank.getId());

        // Agreement agreement = agreementService.getEntity(agreement1.getId());
        // agreementService.deleteEntity(agreement.getId());


        UserRole userRole = userRoleService.getEntity(userRole1.getId());

        userRoleService.deleteEntity(userRole.getId());


    }

    @Test
    public void test1GetEntity() throws Exception {


        Agreement agreement2 = agreementService.getEntity(agreement1.getId());
        assertEquals(agreement1, agreement2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Agreement> agreementList1 = new ArrayList<Agreement>();
        agreementList1.add(agreement1);


        List<Agreement> agreementList2 = agreementService.searchEntityByName(agreement1.getNumber());
        assertEquals(agreementList1, agreementList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Agreement agreement2 = agreementService.getEntityByPK(agreement1);
        assertEquals(agreement1, agreement2);


    }


    @Test
    public void test7Update() throws Exception {

        agreement1.setNumber("010203");
        agreementService.updateEntity(agreement1);
        Agreement agreement2 = agreementService.getEntity(agreement1.getId());

        assertEquals(agreement1, agreement2);


    }


    @Test
    public void test8DeleteById() throws Exception {


        agreementService.deleteEntity(agreement1.getId());


        Agreement agreement2 = agreementService.getEntity(agreement1.getId());

        assertNull(agreement2);


    }


}
