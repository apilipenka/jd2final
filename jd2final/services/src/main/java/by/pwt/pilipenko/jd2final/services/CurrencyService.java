package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.dao.interfaces.ICurrencyDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CurrencyService extends AbstractEntityService<Currency> implements ICurrencyService {

    @Autowired
    ICurrencyDAO currencyDAO;


    public List<Currency> searchEntityByName(String name) {

        Currency entity = new Currency();
        if (name != null && !name.equals("")) {
            entity.setCode(name);
            entity.setMnemoCode(name);
            entity.setName(name);
        }

        return currencyDAO.findEntityByEntity(entity);

    }


}
