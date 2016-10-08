package by.pwt.pilipenko.jd2final.services.interfaces;

import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;

import java.util.List;

/**
 * Created by Darrko on 04.10.2016.
 */


public interface IExchangeRateService extends IService<ExchangeRate> {
    List<ExchangeRate> searchEntityParent(Integer currencyId, Integer targetCurrencyId) throws Exception;

}