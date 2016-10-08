package by.pwt.pilipenko.jd2final.dao.interfaces;


import by.pwt.pilipenko.jd2final.dao.entities.Account;

import java.util.List;

/**
 * Created by apilipenka on 9/1/2016.
 */
public interface IAccountDAO extends BaseDAO<Account> {
    List<Account> findAllWithPagination(int page, int recordsPerPage);

    List<Account> findEntityByEntityWithPagination(Account entity, int page, int recordsPerPage);

    long getRecordsCountEntityByEntity(Account entity);

    long getAllRecordsCount();
}
