package by.pwt.pilipenko.jd2final.services.interfaces;

import by.pwt.pilipenko.jd2final.dao.entities.AbstractEntity;

import java.util.List;

/**
 * Created by Darrko on 04.10.2016.
 */
public interface IService<T extends AbstractEntity> {

    List<T> getAllEntities();

    List<T> searchEntityByName(String name);

    T getEntity(int id);

    T loadEntity(int id) throws Exception;

    T getEntityByPK(T entity);

    boolean updateEntity(T entity);

    T insertEntity(T entity);

    boolean deleteEntity(int id);

    void flush();

}
