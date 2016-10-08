package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.*;
import by.pwt.pilipenko.jd2final.dao.exceptions.InsufficientFundsException;
import by.pwt.pilipenko.jd2final.services.exceptions.AccountNotFoundException;
import by.pwt.pilipenko.jd2final.services.interfaces.*;
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
import java.util.Calendar;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest
        extends Assert {
    private static Bank bank1;
    private static User user1;
    private static Agreement agreement1;
    private static UserRole userRole1;
    private static Currency currency1;
    private static Account account1;
    private static ExchangeRate exchangeRate1;
    @Autowired
    private IBankService bankService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAgreementService agreementService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IExchangeRateService exchangeRateService;

    @Before
    public void init() {

        /*Account acc = new Account();
        acc.setNumber("197777719");
        acc = accountService.getEntityByPK(acc);*/

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

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyService.insertEntity(currency);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency);
        exchangeRate.setTargetCurrency(currency);
        exchangeRate.setRate(1);

        try {
            exchangeRate.setRateDate(format.parse(format.format(Calendar.getInstance().getTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //exchangeRate1 = exchangeRateService.insertEntity(exchangeRate);

        currency.addExchangeRate(exchangeRate);
        currencyService.updateEntity(currency1);

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account1 = accountService.insertEntity(account);


        exchangeRate1 = exchangeRateService.getEntityByPK(exchangeRate);

    }

    @After
    public void close() {
        Account account = accountService.getEntityByPK(account1);
        if (account != null)
            accountService.deleteEntity(account.getId());
        agreementService.deleteEntity(agreement1.getId());
        Bank bank = bankService.getEntityByPK(bank1);
        bankService.deleteEntity(bank.getId());
        User user = userService.getEntityByPK(user1);
        userService.deleteEntity(user1.getId());


        if (currency1 != null) {
            Currency currency = currencyService.getEntity(currency1.getId());
            if (currency != null) {
                currencyService.deleteEntity(currency1.getId());
            }
        }


        if (userRole1 != null) {
            UserRole userRole = userRoleService.getEntity(userRole1.getId());
            if (userRole != null)
                userRoleService.deleteEntity(userRole1.getId());
        }
    }

    @Test
    public void test1GetEntity() throws Exception {


        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1, account2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Account> accountList1 = new ArrayList<>();
        accountList1.add(account1);


        List<Account> accountList2 = accountService.searchEntityByName(account1.getNumber());
        assertEquals(accountList1, accountList2);


    }


    @Test
    public void test3FindEntityByPK() throws Exception {

        Account account2 = accountService.getEntityByPK(account1);
        assertEquals(account1, account2);


    }


    @Test
    public void test4Update() throws Exception {

        account1.setAmount(100);
        accountService.updateEntity(account1);
        Account account2 = accountService.getEntity(account1.getId());

        assertEquals(account1, account2);


    }

    @Test(expected = AccountNotFoundException.class)
    public void test61AddMoney() throws Exception {

        accountService.addMoney("Breeeeeeeed", 200);
        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1, account2);

    }

    @Test
    public void test62AddMoney() throws Exception {

        accountService.addMoney(account1.getNumber(), 200);
        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1.getAmount(), account2.getAmount(), 200);

    }

    @Test
    public void test63getMoney() throws Exception {

        accountService.addMoney(account1.getNumber(), 200);
        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1.getAmount() + 200, account2.getAmount(), 200);

    }

    @Test(expected = InsufficientFundsException.class)
    public void test64getMoney() throws Exception {

        accountService.getMoney(account1.getNumber(), 1300000);
        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1.getAmount() + 200, account2.getAmount(), 200);

    }

    @Test
    public void test9transferMoney() throws Exception {

        account1.setAmount(100);
        accountService.updateEntity(account1);

        Account account2 = new Account(); //accountService.getEntity(account1.getId());
        account2.setNumber("123321567");
        account2.setAmount(0);
        Agreement agreement = agreementService.getEntityByPK(agreement1);
        account2.setAgreement(agreement1);
        Currency currency = currencyService.getEntityByPK(currency1);
        account2.setCurrency(currency);

        account2 = accountService.insertEntity(account2);
        accountService.flush();

        accountService.transferMoney(account1.getNumber(), account2.getNumber(), 60);
        account1 = accountService.getEntity(account1.getId());
        account2 = accountService.getEntity(account2.getId());
        assertEquals(40, account1.getAmount(), 0);
        assertEquals(60, account2.getAmount(), 0);

        accountService.deleteEntity(account2.getId());

    }

    @Test
    public void test8DeleteById() throws Exception {

        accountService.deleteEntity(account1.getId());


        Account account2 = accountService.getEntity(account1.getId());

        assertNull(account2);

    }


}
