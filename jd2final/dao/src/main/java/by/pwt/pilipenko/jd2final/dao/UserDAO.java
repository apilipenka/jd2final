package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.interfaces.IUserDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class UserDAO extends AbstractEntityDAO<User> implements IUserDAO {


    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(User entity) {

        Query query = getSession().createQuery("delete from User where personalNumber = :personalNumber");
        query.setParameter("personalNumber", entity.getPersonalNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<User> findEntityByEntity(User entity) {
        Query query = getSession().createQuery("from User where personalNumber=COALESCE(:personalNumber, personalNumber)");
        query.setParameter("personalNumber", entity.getPersonalNumber());
        return (List<User>) query.list();
    }

    @Override
    public User findEntityByPK(User entity) {

        Query query = getSession().createQuery("from User where login=:login");
        query.setParameter("login", entity.getLogin());
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> findAll() {
        List<User> list;

        list = (List<User>) getSession().createQuery("from User").list();
        return list;


    }

}
