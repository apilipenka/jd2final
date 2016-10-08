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
public class CardDaoTest
        extends Assert {


    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    private static Account account1;
    private static Currency currency1;
    private static Card card1;
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

        Card card = new Card();
        card.setNumber("122333444555");
        card.setName("Aliaksandr Pilipenka");
        try {
            card.setValidToDate(format.parse("10.01.2017"));
        } catch (ParseException e) {
            //it is not possible

        }
        card.setAccount(account1);
        card1 = cardDAO.insert(card);
    }

    @After
    public void close() {

        accountDAO.delete(account1);
        agreementDAO.delete(agreement1);
        bankDAO.delete(bank1);
        userDAO.delete(user1);
        userRoleDAO.delete(userRole1);
        currencyDAO.delete(currency1);

        cardDAO.delete(card1);

    }

    @Test
    public void test1FindById() {


        Card card2 = cardDAO.findEntityById(card1.getId());
        assertEquals(card1, card2);


    }

    @Test
    public void test2FindByEntity() {

        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(card1);

        List<Card> cardList2 = cardDAO.findEntityByEntity(card1);
        assertEquals(cardList1, cardList2);


    }

    @Test
    public void test6FindEntityByPK() {
        Card card2 = cardDAO.findEntityByPK(card1);
        assertEquals(card1, card2);

    }


    @Test
    public void test7Update() {


        card1.setNumber("19777911977989");

        cardDAO.update(card1);
        Card card2 = cardDAO.findEntityById(card1.getId());


        assertEquals(card1, card2);


    }


    @Test
    public void test8DeleteById() {

        cardDAO.delete(card1.getId());

        Card card2 = cardDAO.findEntityById(card1.getId());
        assertNull(card2);

    }

    @Test
    public void test9DeleteByEntity() {


        cardDAO.delete(card1);
        cardDAO.flush();

        List<Card> accountList2 = cardDAO.findEntityByEntity(card1);

        assertEquals(accountList2.size(), 0);


    }


}
