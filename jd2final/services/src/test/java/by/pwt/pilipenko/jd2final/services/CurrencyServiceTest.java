package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyServiceTest
        extends Assert {

    private static Currency currency1;
    @Autowired
    private ICurrencyService currencyService;

    @Before
    public void init() throws NamingException, ClassNotFoundException, SQLException {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyService.insertEntity(currency);

    }

    @After
    public void close() throws SQLException {
        if (currency1 != null) {
            Currency currency = currencyService.getEntity(currency1.getId());
            if (currency != null) {
                currencyService.deleteEntity(currency1.getId());
            }
        }
    }

    @Test
    public void test1GetEntity() throws Exception {


        Currency currency2 = currencyService.getEntity(currency1.getId());
        assertEquals(currency1, currency2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency1);


        List<Currency> currencyList2 = currencyService.searchEntityByName(currency1.getMnemoCode());
        assertEquals(currencyList1, currencyList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Currency currency2 = currencyService.getEntityByPK(currency1);
        assertEquals(currency1, currency2);


    }


    @Test
    public void test7Update() throws Exception {


        currency1.setName("Russian rubble test");
        currencyService.updateEntity(currency1);
        Currency currency2 = currencyService.getEntity(currency1.getId());

        assertEquals(currency1, currency2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        currencyService.deleteEntity(currency1.getId());


        Currency currency2 = currencyService.getEntity(currency1.getId());

        assertNull(currency2);


    }


}
