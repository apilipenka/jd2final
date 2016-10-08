package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAccountDAO;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAgreementDAO;
import by.pwt.pilipenko.jd2final.services.exceptions.AccountNotFoundException;
import by.pwt.pilipenko.jd2final.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountService extends AbstractEntityService<Account> implements IAccountService {


    @Autowired
    IAccountDAO accountDAO;
    @Autowired
    IAgreementDAO agreementDAO;

    public List<Account> searchEntityByName(String name) {
        Account entity = new Account();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        return accountDAO.findEntityByEntity(entity);

    }


    public void getMoney(String accountNumber, double amount) throws Exception {

        Account account = new Account();
        account.setNumber(accountNumber);
        Account account1 = this.getEntityByPK(account);
        if (account1 != null) {
            account1.getMoney(amount);
            this.updateEntity(account1);
        } else
            throw new AccountNotFoundException("Account " + accountNumber + " not found");

    }

    public void addMoney(String accountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(accountNumber);
        Account account1 = this.getEntityByPK(account);
        if (account1 != null) {
            account1.addMoney(amount);
            this.updateEntity(account1);
        } else
            throw new AccountNotFoundException("Account " + accountNumber + " not found");

    }

    //TODO Add program transaction
    public void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(creditAccountNumber);
        Account creditAccount = this.getEntityByPK(account);
        if (creditAccount == null)
            throw new AccountNotFoundException("Account " + creditAccountNumber + " not found");
        account.setNumber(debitAccountNumber);
        Account debitAccount = this.getEntityByPK(account);
        if (debitAccount == null)
            throw new AccountNotFoundException("Account " + debitAccountNumber + " not found");

        creditAccount.getMoney(amount);
        this.updateEntity(creditAccount);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        debitAccount.addMoney(debitAccount.getCurrency().getRate(format.parse(format.format(Calendar.getInstance().getTime())), creditAccount.getCurrency()) * amount);
        this.updateEntity(debitAccount);

    }


    public List<Account> searchEntityByNameWithPagination(String name, int page, int recordsPerPage) {

        Account entity = new Account();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        return accountDAO.findEntityByEntityWithPagination(entity, page, recordsPerPage);

    }

    public List<Account> getAllEntitiesWithPagination(int page, int recordsPerPage) {
        return accountDAO.findAllWithPagination(page, recordsPerPage);

    }

    public long getCountEntityByNameWithPagination(String name) {

        Account entity = new Account();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        return accountDAO.getRecordsCountEntityByEntity(entity);

    }

    public long getCountAllEntitiesWithPagination() {
        return accountDAO.getAllRecordsCount();

    }

    @Override
    public boolean deleteEntity(int id) {
        Account account = accountDAO.findEntityById(id);
        Agreement agreement = agreementDAO.findEntityByPK(account.getAgreement());
        agreement.deleteAccount(account);
        agreementDAO.update(agreement);

        return true;
    }




}
