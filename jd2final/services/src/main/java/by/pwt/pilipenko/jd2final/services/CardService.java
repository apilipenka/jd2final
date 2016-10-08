package by.pwt.pilipenko.jd2final.services;


import by.pwt.pilipenko.jd2final.dao.entities.Account;
import by.pwt.pilipenko.jd2final.dao.entities.Card;
import by.pwt.pilipenko.jd2final.dao.interfaces.IAccountDAO;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICardDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CardService extends AbstractEntityService<Card> implements ICardService {

    @Autowired
    ICardDAO cardDAO;
    @Autowired
    IAccountDAO accountDAO;

    public List<Card> searchEntityByName(String name) {

        Card entity = new Card();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }


        List<Card> list = cardDAO.findEntityByEntity(entity);
        return list;
    }

    @Override
    public boolean deleteEntity(int id) {
        Card card = cardDAO.findEntityById(id);
        Account account = accountDAO.findEntityByPK(card.getAccount());
        account.deleteCard(card);

        accountDAO.update(account);

        return true;
    }

}
