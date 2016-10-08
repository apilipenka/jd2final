package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Transfer;
import by.pwt.pilipenko.jd2final.dao.interfaces.ITransferDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class TransferDAO extends AbstractEntityDAO<Transfer> implements ITransferDAO {


    public TransferDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Transfer entity) {

        Query query = getSession().createQuery("delete from Transfer where creditAccount = :creditAccount and debitAccount = :debitAccount and transferDate=:transferDate");
        query.setParameter("creditAccount", entity.getCreditAccount());
        query.setParameter("debitAccount", entity.getDebitAccount());
        query.setParameter("transferDate", entity.getTransferDate());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Transfer> findEntityByEntity(Transfer entity) {
        Query query = getSession().createQuery("from Transfer where creditAccount=COALESCE(:creditAccount, creditAccount) or debitAccount=COALESCE(:debitAccount, debitAccount) or transferDate=COALESCE(:transferDate, transferDate)");
        query.setParameter("creditAccount", entity.getCreditAccount());
        query.setParameter("debitAccount", entity.getDebitAccount());
        query.setParameter("transferDate", entity.getTransferDate());
        return (List<Transfer>) query.list();
    }

    @Override
    public Transfer findEntityByPK(Transfer entity) {

        Query query = getSession().createQuery("from Transfer where creditAccount=:creditAccount and debitAccount=:debitAccount and transferDate=:transferDate");
        query.setParameter("creditAccount", entity.getCreditAccount());
        query.setParameter("debitAccount", entity.getDebitAccount());
        query.setParameter("transferDate", entity.getTransferDate());
        return (Transfer) query.uniqueResult();
    }

    @Override
    public List<Transfer> findAll() {
        List<Transfer> list;

        list = (List<Transfer>) getSession().createQuery("from Transfer").list();
        return list;


    }

}
