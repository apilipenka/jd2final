package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAgreementDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class AgreementDAO extends AbstractEntityDAO<Agreement> implements IAgreementDAO {


    public AgreementDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Agreement entity) {

        Query query = getSession().createQuery("delete from Agreement where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Agreement> findEntityByEntity(Agreement entity) {
        Query query = getSession().createQuery("from Agreement where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Agreement>) query.list();
    }

    @Override
    public Agreement findEntityByPK(Agreement entity) {

        Query query = getSession().createQuery("from Agreement where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Agreement) query.uniqueResult();
    }

    @Override
    public List<Agreement> findAll() {
        List<Agreement> list;

        list = (List<Agreement>) getSession().createQuery("from Agreement").list();
        return list;


    }

}
