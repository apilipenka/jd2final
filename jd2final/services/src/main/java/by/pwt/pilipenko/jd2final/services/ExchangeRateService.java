package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.Currency;
import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;
import by.pwt.pilipenko.jd2final.dao.interfaces.IExchangeRateDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.ICurrencyService;
import by.pwt.pilipenko.jd2final.services.interfaces.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExchangeRateService extends AbstractEntityService<ExchangeRate> implements IExchangeRateService {

    @Autowired
    IExchangeRateDAO exchangeRateDAO;
    @Autowired
    ICurrencyService currencyService;

    public List<ExchangeRate> searchEntityByName(String name) {

        ExchangeRate entity = new ExchangeRate();
        if (name != null && !name.equals("")) {

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                entity.setRateDate(format.parse(name));
            } catch (ParseException e) {
                //Name may contain not date

            }


            //CurrencyService currencyService = new CurrencyService();
            Currency currency = new Currency();
            currency.setMnemoCode(name);
            Currency currency1 = currencyService.getEntityByPK(currency);
            if (currency1 != null) {
                entity.setCurrency(currency1);
                entity.setTargetCurrency(currency1);
            }
        }

        return exchangeRateDAO.findEntityByEntity(entity);

    }

    public List<ExchangeRate> searchEntityParent(Integer currencyId, Integer targetCurrencyId) throws Exception {

        ExchangeRate entity = new ExchangeRate();
        //CurrencyService currencyService = new CurrencyService();


        if (currencyId != null) {
            Currency currency = currencyService.getEntity(currencyId);
            if (currency != null) {
                entity.setCurrency(currency);
            }
        }

        if (targetCurrencyId != null) {
            Currency currency = currencyService.getEntity(targetCurrencyId);
            if (currency != null) {
                entity.setTargetCurrency(currency);
            }
        }


        return exchangeRateDAO.findEntityByParent(entity);

    }


}
