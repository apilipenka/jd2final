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
public class TransferDaoTest
        extends Assert {


    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    private static Account account1;
    private static Account account11;
    private static Currency currency1;
    private static Card card1;
    private static Transfer transfer1;
    @Autowired
    private CardDAO cardDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private CurrencyDAO currencyDAO;
    @Autowired
    private AgreementDAO agreementDAO;
    @Autowired
    private BankDAO bankDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private TransferDAO transferDAO;

    @Before
    public void init() {
        Bank bank = new Bank();
        bank.setName("Test bank");
        bank.setUNN("19777791");
        bank.setName("Tests bank");

        bank1 = bankDAO.insert(bank);

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

        agreement1 = agreementDAO.insert(agreement);

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyDAO.insert(currency);

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account1 = accountDAO.insert(account);

        account = new Account();
        account.setNumber("1977777191");
        account.setAmount(199);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account11 = accountDAO.insert(account);


        Transfer transfer = new Transfer();
        transfer.setCreditAccount(account1);
        transfer.setDebitAccount(account11);
        transfer.setAmount(100);
        try {
            transfer.setTransferDate(format.parse("10.01.2017"));
        } catch (ParseException e) {
            //it is not possible

        }
        transfer.setComment("Test");


        transfer1 = transferDAO.insert(transfer);
    }

    @After
    public void close() {

        accountDAO.delete(account1);
        accountDAO.delete(account11);
        agreementDAO.delete(agreement1);
        bankDAO.delete(bank1);
        userDAO.delete(user1);
        userRoleDAO.delete(userRole1);
        currencyDAO.delete(currency1);

        transferDAO.delete(transfer1);

    }

    @Test
    public void test1FindById() {


        Transfer transfer2 = transferDAO.findEntityById(transfer1.getId());
        assertEquals(transfer1, transfer2);


    }

    @Test
    public void test2FindByEntity() {

        List<Transfer> transferList1 = new ArrayList<>();
        transferList1.add(transfer1);

        List<Transfer> transferList2 = transferDAO.findEntityByEntity(transfer1);
        assertEquals(transferList1, transferList2);


    }

    @Test
    public void test6FindEntityByPK() {
        Transfer transfer2 = transferDAO.findEntityByPK(transfer1);
        assertEquals(transfer1, transfer2);

    }


    @Test
    public void test7Update() {


        transfer1.setComment("19777911977989");

        transferDAO.update(transfer1);
        Transfer transfer2 = transferDAO.findEntityById(transfer1.getId());


        assertEquals(transfer1, transfer2);


    }


    @Test
    public void test8DeleteById() {

        transferDAO.delete(transfer1.getId());

        Transfer transfer2 = transferDAO.findEntityById(transfer1.getId());
        assertNull(transfer2);

    }

    @Test
    public void test9DeleteByEntity() {


        transferDAO.delete(transfer1);
        transferDAO.flush();

        List<Transfer> transferList2 = transferDAO.findEntityByEntity(transfer1);

        assertEquals(transferList2.size(), 0);


    }


}
