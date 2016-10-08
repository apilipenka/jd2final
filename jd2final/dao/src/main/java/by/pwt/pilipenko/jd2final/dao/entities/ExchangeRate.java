package by.pwt.pilipenko.jd2final.dao.entities;

import by.pwt.pilipenko.jd2final.dao.VO.ExchangeRateVO;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate extends AbstractEntity {

    private static final long serialVersionUID = 873223002647108557L;
    private Date rateDate;
    private float rate;
    private Currency currency;
    private Currency targetCurrency;

    public ExchangeRate() {
    }

    public ExchangeRate(int id, Date rateDate, float rate, Currency currency, Currency targetCurrency) {
        super(id);
        this.rateDate = rateDate;
        this.rate = rate;
        this.currency = currency;
        this.targetCurrency = targetCurrency;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "rate_date", nullable = false, length = 10)
    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }


    @Column(name = "rate")
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "currency_id", nullable = false)
    public Currency getCurrency() {
        return currency;
    }


    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @ManyToOne
    @JoinColumn(name = "target_currency_id", nullable = false)
    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((targetCurrency == null) ? 0 : targetCurrency.hashCode());
        result = prime * result + Float.floatToIntBits(rate);
        result = prime * result + ((rateDate == null) ? 0 : rateDate.hashCode());
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
        ExchangeRate other = (ExchangeRate) obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (targetCurrency == null) {
            if (other.targetCurrency != null)
                return false;
        } else if (!targetCurrency.equals(other.targetCurrency))
            return false;
        if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate))
            return false;
        if (rateDate == null) {
            if (other.rateDate != null)
                return false;
        } else if (!rateDate.equals(other.rateDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExchangeRate [id=" + getId() + ", rateDate=" + rateDate + ", rate=" + rate + ", currency=" + currency.getMnemoCode()
                + ", targetCurrency=" + targetCurrency.getMnemoCode() + "]";
    }

    public ExchangeRateVO createExchangeRateVO() {
        ExchangeRateVO exchangeRateVO = new ExchangeRateVO();
        exchangeRateVO.setId(getId());

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        exchangeRateVO.setRateDate(df.format(rateDate));
        exchangeRateVO.setRate(rate);
        exchangeRateVO.setCurrencyID(currency.getId());
        exchangeRateVO.setCurrencyCode(currency.getCode());
        exchangeRateVO.setCurrencyMnemoCode(currency.getMnemoCode());
        exchangeRateVO.setTargetCurrencyID(targetCurrency.getId());
        exchangeRateVO.setTargetCurrencyCode(targetCurrency.getCode());
        exchangeRateVO.setTargetCurrencyMnemoCode(targetCurrency.getMnemoCode());

        return exchangeRateVO;
    }


}
