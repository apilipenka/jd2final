package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.AbstractEntity;
import by.pwt.pilipenko.jd2final.dao.interfaces.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;


/**
 * Created by Darrko on 30.08.2016.
 */
@Repository()
public abstract class AbstractEntityDAO<T extends AbstractEntity> implements BaseDAO<T> {
    //private Session session;
    private SessionFactory sessionFactory;

    AbstractEntityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findEntityById(int id) {

        return (T) getSession().get(getPersistentClass(), id);

    }

    public T loadEntity(int id) {

        return (T) getSession().load(getPersistentClass(), id);

    }

    public boolean update(T entity) {
        getSession().saveOrUpdate(entity);
        return true;
    }

    public boolean delete(int id) {

        T entity = findEntityById(id);
        getSession().delete(entity);

        return true;
    }

    public T insert(T entity) {
        getSession().saveOrUpdate(entity); //.save(entity);
        return entity;
    }

    public void flush() {
        getSession().flush();
    }

}
