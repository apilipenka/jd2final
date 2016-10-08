package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.services.interfaces.IBankService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BankServiceTest
        extends Assert {

    private static Bank bank1;
    @Autowired
    private IBankService bankService;

    @Test
    public void test1GetEntity() throws Exception {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("123456123");


        bank1 = bankService.insertEntity(bank);


        Bank bank2 = bankService.getEntity(bank1.getId());
        assertEquals(bank1, bank2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Bank> bankList1 = new ArrayList<>();
        bankList1.add(bank1);


        List<Bank> bankList2 = bankService.searchEntityByName(bank1.getName());
        assertEquals(bankList1, bankList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Bank bank2 = bankService.getEntityByPK(bank1);
        assertEquals(bank1, bank2);


    }


    @Test
    public void test7Update() throws Exception {


        bank1.setName("Russian rubble test");
        bankService.updateEntity(bank1);
        Bank bank2 = bankService.getEntity(bank1.getId());

        assertEquals(bank1, bank2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        bankService.deleteEntity(bank1.getId());


        Bank bank2 = bankService.getEntity(bank1.getId());

        assertNull(bank2);


    }


}
