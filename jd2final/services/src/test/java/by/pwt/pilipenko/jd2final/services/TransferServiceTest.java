package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.*;
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
public class TransferServiceTest
        extends Assert {
    private static Bank bank1;
    private static User user1;
    private static Agreement agreement1;
    private static UserRole userRole1;
    private static Currency currency1;
    private static Account account1;
    private static Account account11;
    private static ExchangeRate exchangeRate1;
    private static Transfer transfer1;
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
    @Autowired
    private ITransferService transferService;

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

        Account account111 = new Account();
        account111.setNumber("1977777191");
        account111.setAmount(199919);
        account111.setAgreement(agreement1);
        account111.setCurrency(currency1);

        account11 = accountService.insertEntity(account111);

        Transfer transfer = new Transfer();
        transfer.setCreditAccount(account1);
        transfer.setDebitAccount(account11);
        try {
            transfer.setTransferDate(format.parse("10.01.1917"));
        } catch (ParseException e) {
            //it is not possible

        }
        transfer.setAmount(100);
        transfer.setComment("test");

        transfer1 = transferService.insertEntity(transfer);

        exchangeRate1 = exchangeRateService.getEntityByPK(exchangeRate);

    }

    @After
    public void close() {

        Transfer transfer = transferService.getEntityByPK(transfer1);
        if (transfer != null)
            transferService.deleteEntity(transfer.getId());

        Account account = accountService.getEntityByPK(account1);
        if (account != null)
            accountService.deleteEntity(account.getId());

        account = accountService.getEntityByPK(account11);
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


        Transfer transfer2 = transferService.getEntity(transfer1.getId());
        assertEquals(transfer1, transfer2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Transfer> transferList1 = new ArrayList<>();
        transferList1.add(transfer1);


        List<Transfer> transferList2 = transferService.searchEntityByName(transfer1.getCreditAccount().getNumber());
        List<Transfer> transferList3 = transferService.searchEntityByName(null);


        assertEquals(transferList1, transferList2);


    }


    @Test
    public void test3FindEntityByPK() throws Exception {

        Transfer transfer2 = transferService.getEntityByPK(transfer1);
        assertEquals(transfer1, transfer2);


    }


    @Test
    public void test4Update() throws Exception {

        transfer1.setAmount(100);
        transferService.updateEntity(transfer1);
        Transfer transfer2 = transferService.getEntity(transfer1.getId());

        assertEquals(transfer1, transfer2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        accountService.deleteEntity(account1.getId());


        Account account2 = accountService.getEntity(account1.getId());

        assertNull(account2);

    }


}
