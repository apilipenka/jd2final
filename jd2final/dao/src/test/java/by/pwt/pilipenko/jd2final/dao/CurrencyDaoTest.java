package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;
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
public class CurrencyDaoTest
        extends Assert {
    private static Currency currency1;
    private static ExchangeRate exchangeRate1;
    @Autowired
    private CurrencyDAO currencyDAO;
    @Autowired
    private ExchangeRateDAO exchangeRateDAO;

    @Before
    public void init() {
        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");


        currency1 = currencyDAO.insert(currency);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(100);
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            exchangeRate.setRateDate(format.parse("10.01.1977"));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        //exchangeRate1 = exchangeRateDAO.insert(exchangeRate);
        currency1.addExchangeRate(exchangeRate1);
        currencyDAO.update(currency1);
        currencyDAO.flush();
    }

    @After
    public void close() {

        if (currency1 != null) {
            Currency currency = new Currency();
            currency.setName(currency1.getName());
            currency.setCode(currency1.getCode());
            currency.setMnemoCode(currency1.getMnemoCode());
            //Currency currency2 = currencyDAO.findEntityByPK(currency);

            currencyDAO.delete(currency);
        }
    }


    @Test
    public void test1FindById() {


        Currency currency2 = currencyDAO.findEntityById(currency1.getId());
        assertEquals(currency1, currency2);


    }

    @Test
    public void test2FindByEntity() {

        List<Currency> currencyList1 = new ArrayList<>();
        currencyList1.add(currency1);

        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency1);
        assertEquals(currencyList1, currencyList2);

    }

    @Test
    public void test6FindEntityByPK() {

        Currency currency2 = currencyDAO.findEntityByPK(currency1);
        assertEquals(currency1, currency2);

    }


    @Test
    public void test7Update() {

        currency1.setName("Russian rubble test");

        currencyDAO.update(currency1);

        Currency currency2 = currencyDAO.findEntityById(currency1.getId());

        assertEquals(currency1, currency2);


    }


    @Test
    public void test8DeleteById() {

        currencyDAO.delete(currency1.getId());

        Currency currency2 = currencyDAO.findEntityById(currency1.getId());

        assertNull(currency2);


    }

    @Test
    public void test9DeleteByEntity() {


        currencyDAO.delete(currency1);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency1);

        assertEquals(currencyList2.size(), 0);


    }


}
