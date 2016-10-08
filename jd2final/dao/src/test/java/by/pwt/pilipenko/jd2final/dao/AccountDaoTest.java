package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.*;
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
public class AccountDaoTest
        extends Assert {
    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    private static Account account1;
    private static Currency currency1;
    private static ExchangeRate exchangeRate1;

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private CurrencyDAO currencyDAO;
    @Autowired
    private AgreementDAO agreementDAO;
    @Autowired
    private ExchangeRateDAO exchangeRateDAO;
    @Autowired
    private BankDAO bankDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;


    @Before
    public void init() {

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

        userRole1 = userRoleDAO.insert(userRole);
        user.setUserRole(userRole1);


        user1 = userDAO.insert(user);


        bank1 = bankDAO.insert(bank);

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

        agreement.setClient(user1);
        agreement.setBank(bank1);

        agreement1 = agreementDAO.insert(agreement);

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyDAO.insert(currency);

        ExchangeRate exchangeRate = new ExchangeRate();
        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        exchangeRate.setRate(1);
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        currency1.addExchangeRate(exchangeRate);

        exchangeRate1 = exchangeRateDAO.insert(exchangeRate);

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account1 = accountDAO.insert(account);

    }


    @After
    public void close() {
        //agreement1 = agreementDAO.findEntityById(agreement1.getId());
        agreementDAO.delete(agreement1);
        //bank1 = bankDAO.findEntityById(bank1.getId());
        bankDAO.delete(bank1);
        //user1 = userDAO.findEntityById(user1.getId());
        userDAO.delete(user1);
        //userRole1 = userRoleDAO.findEntityById(userRole1.getId());
        userRoleDAO.delete(userRole1);
        //exchangeRate1 = exchangeRateDAO.findEntityById(exchangeRate1.getId());
        exchangeRateDAO.delete(exchangeRate1);
        //currency1 = currencyDAO.findEntityById(currency1.getId());
        currencyDAO.delete(currency1);

        accountDAO.delete(account1);

    }


    @Test
    public void test1FindById() {


        Account account2 = accountDAO.findEntityById(account1.getId());
        assertEquals(account2, account1);


    }

    @Test
    public void test2FindByEntity() {

        List<Account> accountList1 = new ArrayList<>();
        accountList1.add(account1);

        List<Account> accountList2 = accountDAO.findEntityByEntity(account1);
        assertEquals(accountList1, accountList2);


    }

    @Test
    public void test6FindEntityByPK() {


        Account account2 = accountDAO.findEntityByPK(account1);
        assertEquals(account1, account2);

    }


    @Test
    public void test7Update() {


        account1.setNumber("19777911977989");

        accountDAO.update(account1);
        Account account2 = accountDAO.findEntityById(account1.getId());

        assertEquals(account1, account2);

    }


    @Test
    public void test8DeleteById() {

        accountDAO.delete(account1.getId());

        Account account2 = accountDAO.findEntityById(account1.getId());
        assertNull(account2);


    }

    @Test
    public void test9DeleteByEntity() {


        accountDAO.delete(account1);
        List<Account> accountList2 = accountDAO.findEntityByEntity(account1);

        assertEquals(accountList2.size(), 0);


    }


}
