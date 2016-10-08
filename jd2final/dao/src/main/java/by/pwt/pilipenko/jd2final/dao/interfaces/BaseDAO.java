package by.pwt.pilipenko.jd2final.dao.interfaces;


import by.pwt.pilipenko.jd2final.dao.entities.AbstractEntity;

import java.util.List;


public interface BaseDAO<E extends AbstractEntity> {

    boolean update(E entity);

    boolean delete(int id);

    boolean delete(E entity);

    E findEntityById(int id);

    E loadEntity(int id);

    List<E> findEntityByEntity(E entity);

    E findEntityByPK(E entity);

    List<E> findAll();

    E insert(E entity);

    void flush();

}