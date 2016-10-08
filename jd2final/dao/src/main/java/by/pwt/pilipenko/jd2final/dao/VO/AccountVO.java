package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class AccountVO extends EntityVO {

    private static final long serialVersionUID = -1598908592281617665L;
    private String number;
    private double amount;
    private int agreementID;
    private String agreementNumber;
    private int agreementBankid;
    private String agreementBankName;
    private String agreementBankUNN;
    private int currencyID;
    private String currencyMnemoCode;

    public AccountVO() {
        super();
    }

    public AccountVO(int id, String number, double amount, int agreementID, String agreementNumber, int agreementBankid, String agreementBankName, String agreementBankUNN, int currencyID, String currencyMnemocode, String currencyCode) {
        super(id);
        this.number = number;
        this.amount = amount;
        this.agreementID = agreementID;
        this.agreementNumber = agreementNumber;
        this.agreementBankid = agreementBankid;
        this.agreementBankName = agreementBankName;
        this.agreementBankUNN = agreementBankUNN;

        this.currencyID = currencyID;
        this.currencyMnemoCode = currencyMnemocode;

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(int agreementID) {
        this.agreementID = agreementID;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyMnemocode() {
        return currencyMnemoCode;
    }

    public void setCurrencyMnemocode(String currencyMnemocode) {
        this.currencyMnemoCode = currencyMnemocode;
    }

    public String getAgreementBankName() {
        return agreementBankName;
    }

    public void setAgreementBankName(String agreementBankName) {
        this.agreementBankName = agreementBankName;
    }

    public String getAgreementBankUNN() {
        return agreementBankUNN;
    }

    public void setAgreementBankUNN(String agreementBankUNN) {
        this.agreementBankUNN = agreementBankUNN;
    }

    public int getAgreementBankid() {
        return agreementBankid;
    }

    public void setAgreementBankid(int agreementBankid) {
        this.agreementBankid = agreementBankid;
    }

    public String getDescription() {
        return number + ' ' + agreementBankName + ' ' + currencyMnemoCode;
    }

}
