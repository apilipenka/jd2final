package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAccountDAO;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class AccountDAO extends AbstractEntityDAO<Account> implements IAccountDAO {

    private static Logger log = Logger.getLogger(AccountDAO.class);


    public AccountDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Account entity) {

        Query query = getSession().createQuery("delete from Account where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Account> findEntityByEntity(Account entity) {
        Query query = getSession().createQuery("from Account where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Account>) query.list();
    }

    @Override
    public Account findEntityByPK(Account entity) {

        Query query = getSession().createQuery("from Account where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Account) query.uniqueResult();
    }

    @Override
    public List<Account> findAll() {
        List<Account> list;

        list = (List<Account>) getSession().createQuery("from Account").list();
        return list;


    }


    public List<Account> findAllWithPagination(int page, int recordsPerPage) {
        try {
            Criteria cr = getSession().createCriteria(Account.class);
            cr.setFirstResult((page - 1) * recordsPerPage);
            cr.setMaxResults(recordsPerPage);
            List<Account> accounts = cr.list();

            if (accounts.isEmpty()) {
                return null;
            } else {
                return accounts;
            }
        } catch (RuntimeException ex) {
            log.error(ex);
            return null;
        }
    }

    public List<Account> findEntityByEntityWithPagination(Account entity, int page, int recordsPerPage) {
        try {
            Criteria cr = getSession().createCriteria(Account.class);
            cr.add(Restrictions.like("number", entity.getNumber() + "%"));
            cr.setFirstResult((page - 1) * recordsPerPage);
            cr.setMaxResults(recordsPerPage);
            List<Account> accounts = cr.list();

            if (accounts.isEmpty()) {
                return null;
            } else {
                return accounts;
            }
        } catch (RuntimeException ex) {
            log.error(ex);
            return null;
        }
    }

    public long getRecordsCountEntityByEntity(Account entity) {
        try {

            //return (Number) session.createCriteria("Book").setProjection(Projections.rowCount()).uniqueResult();

            Criteria cr = getSession().createCriteria(Account.class);
            cr.add(Restrictions.like("number", entity.getNumber() + "%"));
            return (long) cr.setProjection(Projections.rowCount()).uniqueResult();
        } catch (RuntimeException ex) {
            log.error(ex);
            return 0;
        }
    }

    public long getAllRecordsCount() {
        try {
            Criteria cr = getSession().createCriteria(Account.class);
            return (long) cr.setProjection(Projections.rowCount()).uniqueResult();
        } catch (RuntimeException ex) {
            log.error(ex);
            return 0;
        }
    }


}
