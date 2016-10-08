package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.dao.interfaces.IUserRoleDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class UserRoleDAO extends AbstractEntityDAO<UserRole> implements IUserRoleDAO {

    public UserRoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public boolean delete(UserRole entity) {
        Query query = getSession().createQuery("delete from UserRole where name = :name");
        query.setParameter("name", entity.getName());
        int result = query.executeUpdate();

        return true;
    }

    @Override
    public List<UserRole> findEntityByEntity(UserRole entity) {
        Query query = getSession().createQuery("from UserRole where name=COALESCE(:name, name)");
        query.setParameter("name", entity.getName());
        return (List<UserRole>) query.list();
    }

    @Override
    public UserRole findEntityByPK(UserRole entity) {
        Query query = getSession().createQuery("from UserRole where name=:name");
        query.setParameter("name", entity.getName());
        return (UserRole) query.uniqueResult();
    }

    @Override
    public List<UserRole> findAll() {
        List<UserRole> list;

        list = (List<UserRole>) getSession().createQuery("from UserRole").list();
        return list;
    }

}
