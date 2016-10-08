package by.pwt.pilipenko.jd2final.services;


import by.pwt.pilipenko.jd2final.dao.entities.Agreement;
import by.pwt.pilipenko.jd2final.dao.entities.Bank;
import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAgreementDAO;
import by.pwt.pilipenko.jd2final.dao.interfaces.IBankDAO;
import by.pwt.pilipenko.jd2final.dao.interfaces.IUserDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.IAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AgreementService extends AbstractEntityService<Agreement> implements IAgreementService {

    @Autowired
    IAgreementDAO agreementDAO;
    @Autowired
    IBankDAO bankDAO;
    @Autowired
    IUserDAO userDAO;

    public List<Agreement> searchEntityByName(String name) {
        Agreement entity = new Agreement();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }

        return agreementDAO.findEntityByEntity(entity);
    }

    @Override
    public boolean deleteEntity(int id) {
        Agreement agreement = agreementDAO.findEntityById(id);
        Bank bank = bankDAO.findEntityByPK(agreement.getBank());
        User user = userDAO.findEntityByPK(agreement.getClient());
        bank.deleteAgreement(agreement);
        bankDAO.update(bank);


        user.deleteAgreement(agreement);
        userDAO.update(user);


        return true;
    }


}
