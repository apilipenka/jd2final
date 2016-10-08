package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Card;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICardDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class CardDAO extends AbstractEntityDAO<Card> implements ICardDAO {


    public CardDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Card entity) {

        Query query = getSession().createQuery("delete from Card where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Card> findEntityByEntity(Card entity) {
        Query query = getSession().createQuery("from Card where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Card>) query.list();
    }

    @Override
    public Card findEntityByPK(Card entity) {

        Query query = getSession().createQuery("from Card where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Card) query.uniqueResult();
    }

    @Override
    public List<Card> findAll() {
        List<Card> list;

        list = (List<Card>) getSession().createQuery("from Card").list();
        return list;


    }

}
