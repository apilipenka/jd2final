package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Command;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICommandDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.ICommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommandService extends AbstractEntityService<Command> implements ICommandService {

    @Autowired
    ICommandDAO cardDAO;

    public List<Command> searchEntityByName(String name) {

        Command entity = new Command();
        if (name != null && !name.equals("")) {
            entity.setCommand(name);
        }


        return cardDAO.findEntityByEntity(entity);
    }


}
