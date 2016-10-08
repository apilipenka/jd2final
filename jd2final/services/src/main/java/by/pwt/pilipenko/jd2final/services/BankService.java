package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.interfaces.IBankDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BankService extends AbstractEntityService<Bank> implements IBankService {

    @Autowired
    IBankDAO bankDAO;

    public List<Bank> searchEntityByName(String name) {

        Bank entity = new Bank();
        if (name != null && !name.equals("")) {
            entity.setName(name);
        }


        return bankDAO.findEntityByEntity(entity);

    }


}
