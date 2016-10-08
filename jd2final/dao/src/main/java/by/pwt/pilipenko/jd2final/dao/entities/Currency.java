package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.exceptions.RateNotFoundException;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "currencies")
public class Currency extends AbstractEntity {

    private static final long serialVersionUID = -6049197255924255456L;

    private String mnemoCode;
    private String code;
    private String name;

    private Set<ExchangeRate> rates;

    public Currency() {
        super();
        rates = new HashSet<>();
    }

    public Currency(int id, String mnemoCode, String code, String name) {
        super(id);
        this.mnemoCode = mnemoCode;
        this.code = code;
        this.name = name;

        rates = new HashSet<>();

    }

    @Column(name = "mnemo_code", columnDefinition = "VARCHAR2(45) NOT NULL")
    public String getMnemoCode() {
        return mnemoCode;
    }

    public void setMnemoCode(String mnemoCode) {
        this.mnemoCode = mnemoCode;
    }

    @Column(name = "code", columnDefinition = "VARCHAR2(45) NOT NULL")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", columnDefinition = "VARCHAR2(200) NOT NULL")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ExchangeRate> getRates() {
        return rates;
    }


    public void setRates(Set<ExchangeRate> rates) {
        this.rates = rates;
    }


    public ExchangeRate getExchangeRate(Date date, Currency targetCurrency) {
        for (ExchangeRate exchangeRate : rates) {
            if (exchangeRate.getRateDate().equals(date) && exchangeRate.getCurrency().equals(targetCurrency)) {
                return exchangeRate;
            }
        }
        return null;
    }

    public float getRate(Date date, Currency targetCurrency) throws RateNotFoundException {
        for (ExchangeRate exchangeRate : rates) {
            if (exchangeRate.getRateDate().equals(date) && exchangeRate.getCurrency().equals(targetCurrency)) {
                return exchangeRate.getRate();
            }
        }
        throw new RateNotFoundException();
    }

    public void addExchangeRate(ExchangeRate exchangeRate) {
        rates.add(exchangeRate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((mnemoCode == null) ? 0 : mnemoCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Currency other = (Currency) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!mnemoCode.equals(other.mnemoCode))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "currency [id=" + getId() + ", mnemoCode=" + mnemoCode + ", code=" + code + ", name=" + name + ", rates=" + rates + "]";
    }

    public void deleteExchangeRate(ExchangeRate rate) {
        rates.remove(rate);
    }

}
