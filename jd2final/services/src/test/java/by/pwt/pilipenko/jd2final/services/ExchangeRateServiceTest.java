package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
import by.pwt.pilipenko.jd2final.services.interfaces.IExchangeRateService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import java.sql.SQLException;
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
public class ExchangeRateServiceTest
        extends Assert {

    private static Currency currency1;
    private static ExchangeRate exchangeRate1;
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private IExchangeRateService exchangeRateService;

    @Before
    public void init() throws NamingException, ClassNotFoundException, SQLException {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyService.insertEntity(currency);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        exchangeRate.setRate(197);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible
        }

        exchangeRate.setCurrency(currency1);

        exchangeRate1 = exchangeRateService.insertEntity(exchangeRate);
        currency1.addExchangeRate(exchangeRate1);
        currencyService.updateEntity(currency1);

    }

    @After
    public void close() throws SQLException, ClassNotFoundException, NamingException {

        /*if (exchangeRate1!=null) {
            ExchangeRate exchangeRate = exchangeRateService.getEntity(exchangeRate1.getId());
            if (exchangeRate!=null) {
                exchangeRateService.deleteEntity(exchangeRate.getId());
            }
        }*/
        if (currency1 != null) {
            Currency currency = currencyService.getEntity(currency1.getId());
            if (currency != null) {
                currencyService.deleteEntity(currency1.getId());
            }
        }

    }

    @Test
    public void test1GetEntity() throws Exception {


        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test2FindByEntity() {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<ExchangeRate>();
        exchangeRateList1.add(exchangeRate1);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityByName(format.format(exchangeRate1.getRateDate()));
        assertEquals(exchangeRateList1, exchangeRateList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        ExchangeRate exchangeRate2 = exchangeRateService.getEntityByPK(exchangeRate1);
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test61FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(exchangeRate1.getCurrency().getId(), exchangeRate1.getTargetCurrency().getId());
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test62FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(exchangeRate1.getCurrency().getId(), null);
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test63FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(null, exchangeRate1.getTargetCurrency().getId());
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test7Update() throws Exception {


        exchangeRate1.setRate(1);
        exchangeRateService.updateEntity(exchangeRate1);
        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());

        assertEquals(exchangeRate1, exchangeRate2);


    }


    @Test
    public void test8DeleteById() throws Exception {
        Currency c = currencyService.getEntityByPK(exchangeRate1.getCurrency());
        c.deleteExchangeRate(exchangeRate1);
        currencyService.updateEntity(c);
        //exchangeRateService.deleteEntity(exchangeRate1.getId());
        //currencyService.flush();

        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());

        assertNull(exchangeRate2);


    }


}
