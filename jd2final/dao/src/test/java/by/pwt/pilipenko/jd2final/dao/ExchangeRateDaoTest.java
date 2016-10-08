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
public class ExchangeRateDaoTest
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
        currency.setCode("977");
        currency.setMnemoCode("AWP");
        currency.setName("Tests currency");


        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(197);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible

        }


        currency1 = currencyDAO.insert(currency);
        currencyDAO.flush();
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        exchangeRate1 = exchangeRateDAO.insert(exchangeRate);

    }

    @After
    public void close() {


        currencyDAO.delete(currency1);


    }

    @Test
    public void test1FindById() {


        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test2FindByEntity() {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateDAO.findEntityByEntity(exchangeRate1);
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test6FindEntityByPK() {
        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityByPK(exchangeRate1);
        assertEquals(exchangeRate1, exchangeRate2);

    }


    @Test
    public void test7Update() {


        exchangeRate1.setRate(300);
        exchangeRateDAO.flush();
        exchangeRateDAO.update(exchangeRate1);
        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());


        assertEquals(exchangeRate1, exchangeRate2);

    }


    @Test
    public void test8DeleteById() {


        exchangeRateDAO.delete(exchangeRate1.getId());
        exchangeRateDAO.flush();

        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());
        assertNull(exchangeRate2);

    }

    @Test
    public void test9DeleteByEntity() {


        exchangeRateDAO.delete(exchangeRate1);
        exchangeRateDAO.flush();

        List<ExchangeRate> exchangeRateList2 = exchangeRateDAO.findEntityByEntity(exchangeRate1);

        assertEquals(exchangeRateList2.size(), 0);


    }


}
