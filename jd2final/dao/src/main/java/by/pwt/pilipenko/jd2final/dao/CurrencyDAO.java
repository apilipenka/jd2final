package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICurrencyDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class CurrencyDAO extends AbstractEntityDAO<Currency> implements ICurrencyDAO {

    public CurrencyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Currency entity) {

        Query query = getSession().createQuery("delete from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Currency> findEntityByEntity(Currency entity) {
        Query query = getSession().createQuery("from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        return (List<Currency>) query.list();
    }

    @Override
    public Currency findEntityByPK(Currency entity) {

        Query query = getSession().createQuery("from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        return (Currency) query.uniqueResult();
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> list;

        list = (List<Currency>) getSession().createQuery("from Currency").list();
        return list;


    }


}
