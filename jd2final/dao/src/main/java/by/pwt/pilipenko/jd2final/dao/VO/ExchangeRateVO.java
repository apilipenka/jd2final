package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class ExchangeRateVO extends EntityVO {
    private static final long serialVersionUID = -6317606056252046349L;
    private String rateDate;
    private float rate;
    private int currencyID;
    private String currencyMnemoCode;
    private String currencyCode;
    private int targetCurrencyID;
    private String targetCurrencyMnemoCode;
    private String targetCurrencyCode;

    public ExchangeRateVO() {
        super();
    }

    public ExchangeRateVO(int id, String rateDate, float rate, int currencyID, String currencyMnemoCode, String currencyCode, int targetCurrencyID, String targetCurrencyMnemoCode, String targetCurrencyCode) {
        super(id);
        this.rateDate = rateDate;
        this.rate = rate;
        this.currencyID = currencyID;
        this.currencyMnemoCode = currencyMnemoCode;
        this.currencyCode = currencyCode;
        this.targetCurrencyID = targetCurrencyID;
        this.targetCurrencyMnemoCode = targetCurrencyMnemoCode;
        this.targetCurrencyCode = targetCurrencyCode;

    }


    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyMnemoCode() {
        return currencyMnemoCode;
    }

    public void setCurrencyMnemoCode(String currencyMnemoCode) {
        this.currencyMnemoCode = currencyMnemoCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getTargetCurrencyID() {
        return targetCurrencyID;
    }

    public void setTargetCurrencyID(int targetCurrencyID) {
        this.targetCurrencyID = targetCurrencyID;
    }

    public String getTargetCurrencyMnemoCode() {
        return targetCurrencyMnemoCode;
    }

    public void setTargetCurrencyMnemoCode(String targetCurrencyMnemoCode) {
        this.targetCurrencyMnemoCode = targetCurrencyMnemoCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }
}
