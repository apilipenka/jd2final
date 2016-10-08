package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;
import by.pwt.pilipenko.jd2final.dao.interfaces.IExchangeRateDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class ExchangeRateDAO extends AbstractEntityDAO<ExchangeRate> implements IExchangeRateDAO {

    public ExchangeRateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(ExchangeRate entity) {

        Query query = getSession().createQuery("delete from ExchangeRate where currency = :currency and targetCurrency = :targetCurrency");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<ExchangeRate> findEntityByEntity(ExchangeRate entity) {
        Query query = getSession().createQuery("from ExchangeRate where rateDate=COALESCE(:rateDate, rateDate)");
        query.setParameter("rateDate", entity.getRateDate());
        return (List<ExchangeRate>) query.list();
    }

    @Override
    public ExchangeRate findEntityByPK(ExchangeRate entity) {

        Query query = getSession().createQuery("from ExchangeRate where currency = :currency and targetCurrency = :targetCurrency and rateDate=:rateDate");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        query.setParameter("rateDate", entity.getRateDate());
        return (ExchangeRate) query.uniqueResult();
    }

    @Override
    public List<ExchangeRate> findAll() {
        List<ExchangeRate> list;

        list = (List<ExchangeRate>) getSession().createQuery("from ExchangeRate").list();
        return list;


    }


    @Override
    public List<ExchangeRate> findEntityByParent(ExchangeRate entity) {
        //currency_id=ifnull(?,currency_id) and target_currency_id=ifnull(?,target_currency_id)

        Query query = getSession().createQuery("from ExchangeRate where currency=COALESCE(:currency, currency) and  targetCurrency=COALESCE(:targetCurrency, targetCurrency)");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        return (List<ExchangeRate>) query.list();
    }
}
