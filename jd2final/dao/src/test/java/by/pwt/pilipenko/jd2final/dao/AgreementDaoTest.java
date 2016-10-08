package by.pwt.pilipenko.jd2final.dao;


import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingException;
import java.sql.SQLException;
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
public class AgreementDaoTest
        extends Assert {


    private static Logger log = Logger.getLogger(AgreementDaoTest.class);

    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    @Autowired
    private AgreementDAO agreementDAO;
    @Autowired
    private BankDAO bankDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Before
    public void init() throws NamingException, ClassNotFoundException, SQLException {

        log.info("before");

        Bank bank = new Bank();
        bank.setName("Test bank");
        bank.setUNN("19777791");
        bank.setName("Tests bank");


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


        bank1 = bankDAO.insert(bank);
        userRole1 = userRoleDAO.insert(userRole);
        user.setUserRole(userRole1);
        user1 = userDAO.insert(user);
        agreement.setBank(bank1);
        agreement.setClient(user1);
        agreement1 = agreementDAO.insert(agreement);

    }

    @After
    public void close() {
        log.info("after");
        bank1 = bankDAO.findEntityById(bank1.getId());
        bankDAO.delete(bank1);
        user1 = userDAO.findEntityById(user1.getId());
        userDAO.delete(user1);
        userRole1 = userRoleDAO.findEntityById(userRole1.getId());
        userRoleDAO.delete(userRole1);

        agreementDAO.delete(agreement1);

    }


    @Test
    public void test1FindById() {


        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
        assertEquals(agreement1, agreement2);

    }

    @Test
    public void test2FindByEntity() {

        List<Agreement> agreementList1 = new ArrayList<>();
        agreementList1.add(agreement1);

        List<Agreement> agreementList2 = agreementDAO.findEntityByEntity(agreement1);
        assertEquals(agreementList1, agreementList2);


    }

    @Test
    public void test6FindEntityByPK() {
        Agreement agreement2 = agreementDAO.findEntityByPK(agreement1);
        assertEquals(agreement1, agreement2);

    }


    @Test
    public void test7Update() {


        agreement1.setNumber("19777911977");

        agreementDAO.update(agreement1);
        agreementDAO.flush();


        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());


        assertEquals(agreement1, agreement2);


    }


    @Test
    public void test8DeleteById() {

        agreementDAO.delete(agreement1.getId());
        agreementDAO.flush();
        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
        assertNull(agreement2);


    }

    @Test
    public void test9DeleteByEntity() {


        agreementDAO.delete(agreement1);


        List<Agreement> agreementList2 = agreementDAO.findEntityByEntity(agreement1);

        assertEquals(agreementList2.size(), 0);


    }


}
