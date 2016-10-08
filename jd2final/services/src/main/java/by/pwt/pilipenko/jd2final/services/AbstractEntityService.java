package by.pwt.pilipenko.jd2final.services;


import by.pwt.pilipenko.jd2final.dao.entities.AbstractEntity;
import by.pwt.pilipenko.jd2final.dao.interfaces.BaseDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractEntityService<T extends AbstractEntity> implements IService<T> {

    @Autowired
    private BaseDAO<T> baseDao;

    public AbstractEntityService() {
    }

    @Autowired
    public AbstractEntityService(BaseDAO<T> baseDao) {
        this.baseDao = baseDao;
    }


    public List<T> getAllEntities() {
        return baseDao.findAll();
    }

    public abstract List<T> searchEntityByName(String name);

    public T getEntity(int id) {
        return baseDao.findEntityById(id);
    }

    public T loadEntity(int id) {
        return baseDao.loadEntity(id);
    }

    public T getEntityByPK(T entity) {
        return baseDao.findEntityByPK(entity);
    }

    public boolean updateEntity(T entity) {
        return baseDao.update(entity);
    }

    public T insertEntity(T entity) {
        return baseDao.insert(entity);
    }

    public boolean deleteEntity(int id) {
        return baseDao.delete(id);
    }

    public void flush() {
        baseDao.flush();
    }

}
