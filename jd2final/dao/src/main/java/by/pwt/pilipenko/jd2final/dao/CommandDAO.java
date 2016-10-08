package by.pwt.pilipenko.jd2final.dao;

import by.pwt.pilipenko.jd2final.dao.entities.Command;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICommandDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class CommandDAO extends AbstractEntityDAO<Command> implements ICommandDAO {


    public CommandDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public boolean delete(Command entity) {

        Query query = getSession().createQuery("delete from Command where command = :command");
        query.setParameter("command", entity.getCommand());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Command> findEntityByEntity(Command entity) {
        Query query = getSession().createQuery("from Command where command=COALESCE(:command, command)");
        query.setParameter("command", entity.getCommand());
        return (List<Command>) query.list();
    }

    @Override
    public Command findEntityByPK(Command entity) {

        Query query = getSession().createQuery("from Command where command=:command");
        query.setParameter("command", entity.getCommand());
        return (Command) query.uniqueResult();
    }

    @Override
    public List<Command> findAll() {
        List<Command> list;

        list = (List<Command>) getSession().createQuery("from Command").list();
        return list;


    }

}
