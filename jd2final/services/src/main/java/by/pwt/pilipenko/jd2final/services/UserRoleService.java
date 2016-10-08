package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.UserRole;
import by.pwt.pilipenko.jd2final.dao.interfaces.IUserRoleDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserRoleService extends AbstractEntityService<UserRole> implements IUserRoleService {

    @Autowired
    IUserRoleDAO userRoleDAO;

    public List<UserRole> searchEntityByName(String name) {

        UserRole entity = new UserRole();
        if (name != null && !name.equals("")) {
            entity.setName(name);
        }

        return userRoleDAO.findEntityByEntity(entity);

    }


}
