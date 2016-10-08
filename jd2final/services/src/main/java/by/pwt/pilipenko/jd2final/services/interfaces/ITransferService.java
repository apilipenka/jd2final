package by.pwt.pilipenko.jd2final.services.interfaces;

import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.entities.Transfer;

/**
 * Created by Darrko on 04.10.2016.
 */


public interface ITransferService extends IService<Transfer> {
    void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception;


}