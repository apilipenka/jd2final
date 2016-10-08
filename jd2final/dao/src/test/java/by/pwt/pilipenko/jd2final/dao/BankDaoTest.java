package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class BankDaoTest
        extends Assert {

    private static Bank bank1;
    @Autowired
    private BankDAO bankDAO;

    @Before
    public void init() {
        bankDAO.flush();
        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("121313");

        bank1 = bankDAO.insert(bank);
        bankDAO.flush();


    }

    @After
    public void close() {

        bankDAO.delete(bank1);
    }


    @Test
    public void test1FindById() {


        Bank bank2 = bankDAO.findEntityById(bank1.getId());
        assertEquals(bank1, bank2);


    }

    @Test
    public void test2FindByEntity() {

        List<Bank> bankList1 = new ArrayList<>();
        bankList1.add(bank1);


        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank1);
        assertEquals(bankList1, bankList2);


    }

    @Test
    public void test6FindEntityByPK() {


        Bank bank2 = bankDAO.findEntityByPK(bank1);
        assertEquals(bank1, bank2);

        assertEquals(bank1, bank1);


    }


    @Test
    public void test7Update() {


        bank1.setName("AgropromBank");
        bankDAO.update(bank1);
        bankDAO.flush();
        Bank bank2 = bankDAO.findEntityById(bank1.getId());

        assertEquals(bank1, bank2);


    }


    @Test
    public void test8DeleteById() {


        bankDAO.delete(bank1.getId());
        bankDAO.flush();
        Bank bank2 = bankDAO.findEntityById(bank1.getId());

        assertNull(bank2);

    }

    @Test
    public void test9DeleteByEntity() {


        bankDAO.delete(bank1);
        bankDAO.flush();
        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank1);

        assertEquals(0, bankList2.size());


    }


}
