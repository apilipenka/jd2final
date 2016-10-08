package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.interfaces.IBankDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class BankDAO extends AbstractEntityDAO<Bank> implements IBankDAO {


    BankDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Bank entity) {
        Query query = getSession().createQuery("delete from Bank where unn = :unn");
        query.setParameter("unn", entity.getUNN());
        int result = query.executeUpdate();
        return result == 1;
    }


    @Override
    public List<Bank> findEntityByEntity(Bank entity) {
        Query query = getSession().createQuery("from Bank where name=COALESCE(:name, name) and unn=COALESCE(:unn, unn)");
        query.setParameter("name", entity.getName());
        query.setParameter("unn", entity.getUNN());
        return (List<Bank>) query.list();
    }

    @Override
    public Bank findEntityByPK(Bank entity) {
        Query query = getSession().createQuery("from Bank where unn=:unn");
        query.setParameter("unn", entity.getUNN());
        return (Bank) query.uniqueResult();
    }

    @Override
    public List<Bank> findAll() {
        List<Bank> list;

        list = (List<Bank>) getSession().createQuery("from Bank").list();
        return list;
    }

}
