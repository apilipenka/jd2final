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
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CardServiceTest
        extends Assert {
    private static Bank bank1;
    private static User user1;
    private static Agreement agreement1;
    private static UserRole userRole1;
    private static Currency currency1;
    private static Account account1;
    private static Card card1;
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
    private ICardService cardService;

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
        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");


        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency);

        currency1 = currencyService.insertEntity(currency);

        account1 = accountService.insertEntity(account);

        //currency1 = currencyService.getEntityByPK(currency);

        Card card = new Card();
        card.setNumber("122333444555");
        card.setName("Aliaksandr Pilipenka");
        try {
            card.setValidToDate(format.parse("10.01.2017"));
        } catch (ParseException e) {
            //it is not possible
        }
        card.setAccount(account1);

        card1 = cardService.insertEntity(card);
    }

    @After
    public void close() {
        if (agreement1 != null) {
            Agreement agreement = agreementService.getEntity(agreement1.getId());
            if (agreement != null) {
                agreementService.deleteEntity(agreement1.getId());
            }
        }

        if (user1 != null) {
            User user = userService.getEntity(user1.getId());
            if (user != null)
                userService.deleteEntity(user.getId());
        }

        if (bank1 != null) {
            Bank bank = bankService.getEntity(bank1.getId());
            if (bank != null) {
                bankService.deleteEntity(bank.getId());
            }
        }



        if (account1 != null) {
            Account account = accountService.getEntity(account1.getId());
            if (account != null) {
                accountService.deleteEntity(account.getId());
            }
        }


        if (card1 != null) {
            Card card = cardService.getEntity(card1.getId());
            if (card != null) {
                cardService.deleteEntity(card.getId());
            }
        }


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


        Card card2 = cardService.getEntity(card1.getId());
        assertEquals(card1, card2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(card1);


        List<Card> cardList2 = cardService.searchEntityByName(card1.getNumber());
        assertEquals(cardList1, cardList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Card card2 = cardService.getEntityByPK(card1);
        assertEquals(card1, card2);


    }


    @Test
    public void test7Update() throws Exception {

        card1.setNumber("010203");
        cardService.updateEntity(card1);
        Card card2 = cardService.getEntity(card1.getId());

        assertEquals(card1, card2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        cardService.deleteEntity(card1.getId());


        Card card2 = cardService.getEntity(card1.getId());

        assertNull(card2);


    }


}
