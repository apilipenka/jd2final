package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.entities.Transfer;
import by.pwt.pilipenko.jd2final.dao.interfaces.ITransferDAO;
import by.pwt.pilipenko.jd2final.services.exceptions.AccountNotFoundException;
import by.pwt.pilipenko.jd2final.services.interfaces.IAccountService;
import by.pwt.pilipenko.jd2final.services.interfaces.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TransferService extends AbstractEntityService<Transfer> implements ITransferService {

    @Autowired
    ITransferDAO transferDAO;
    @Autowired
    IAccountService accountService;


    public List<Transfer> searchEntityByName(String name) {
        Transfer entity = new Transfer();
        if (name != null && !name.equals("")) {
            Account account = new Account();
            account.setNumber(name);
            Account account1 = accountService.getEntityByPK(account);


            entity.setCreditAccount(account1);
            entity.setDebitAccount(account1);

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                entity.setTransferDate(format.parse(name));
            } catch (ParseException e) {
                //Name may contain not date

            }

        }
        return transferDAO.findEntityByEntity(entity);

    }


    //TODO Add program transaction
    public void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(creditAccountNumber);
        Account creditAccount = accountService.getEntityByPK(account);
        if (creditAccount == null)
            throw new AccountNotFoundException("Account " + creditAccountNumber + " not found");
        account.setNumber(debitAccountNumber);
        Account debitAccount = accountService.getEntityByPK(account);
        if (debitAccount == null)
            throw new AccountNotFoundException("Account " + debitAccountNumber + " not found");

        creditAccount.getMoney(amount);
        accountService.updateEntity(creditAccount);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        debitAccount.addMoney(debitAccount.getCurrency().getRate(format.parse(format.format(Calendar.getInstance().getTime())), creditAccount.getCurrency()) * amount);
        accountService.updateEntity(debitAccount);

    }


}
