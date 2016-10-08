package by.pwt.pilipenko.jd2final.services.interfaces;

import by.pwt.pilipenko.jd2final.dao.entities.Account;

import java.util.List;

/**
 * Created by Darrko on 04.10.2016.
 */


public interface IAccountService extends IService<Account> {
    void getMoney(String accountNumber, double amount) throws Exception;

    void addMoney(String accountNumber, double amount) throws Exception;

    void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception;

    List<Account> searchEntityByNameWithPagination(String name, int page, int recordsPerPage);

    List<Account> getAllEntitiesWithPagination(int page, int recordsPerPage);

    long getCountEntityByNameWithPagination(String name);

    long getCountAllEntitiesWithPagination();

}