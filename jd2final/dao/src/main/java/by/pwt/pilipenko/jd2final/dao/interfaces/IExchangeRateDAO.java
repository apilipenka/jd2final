package by.pwt.pilipenko.jd2final.dao.interfaces;


import by.pwt.pilipenko.jd2final.dao.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by apilipenka on 9/1/2016.
 */
public interface IExchangeRateDAO extends BaseDAO<ExchangeRate> {
    List<ExchangeRate> findEntityByParent(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException;

}
