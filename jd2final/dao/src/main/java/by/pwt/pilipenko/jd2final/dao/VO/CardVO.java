package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class CardVO extends EntityVO {
    private static final long serialVersionUID = -3230612275404619415L;
    private String number;
    private String name;
    private String validToDate;
    private int accountID;
    private String accountNumber;
    private int accountAgreementId;
    private String accountAgreementNumber;
    private int accountAgreementBankId;
    private String accountAgreementBankName;
    private String accountAgreementBankUNN;
    private int accountCurrencyId;
    private String accountCurrencyMnemoCode;

    public CardVO() {
        super();
    }

    public CardVO(int id, String number, String name, String validToDate, int accountID, String accountNumber, int accountAgreementId, String accountAgreementNumber, int accountAgreementBankId, String accountAgreementBankName, String accountAgreementBankUNN, int accountCurrencyId, String accountCurrencyMnemoCode) {
        super(id);
        this.number = number;
        this.name = name;
        this.validToDate = validToDate;
        this.accountID = accountID;
        this.accountNumber = accountNumber;
        this.accountAgreementId = accountAgreementId;
        this.accountAgreementNumber = accountAgreementNumber;
        this.accountAgreementBankId = accountAgreementBankId;
        this.accountAgreementBankName = accountAgreementBankName;
        this.accountAgreementBankUNN = accountAgreementBankUNN;
        this.accountCurrencyId = accountCurrencyId;
        this.accountCurrencyMnemoCode = accountCurrencyMnemoCode;
    }

    public int getAccountAgreementBankId() {
        return accountAgreementBankId;
    }

    public void setAccountAgreementBankId(int accountAgreementBankId) {
        this.accountAgreementBankId = accountAgreementBankId;
    }

    public int getAccountCurrencyId() {
        return accountCurrencyId;
    }

    public void setAccountCurrencyId(int accountCurrencyId) {
        this.accountCurrencyId = accountCurrencyId;
    }

    public int getAccountAgreementId() {
        return accountAgreementId;
    }

    public void setAccountAgreementId(int accountAgreementId) {
        this.accountAgreementId = accountAgreementId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountCurrencyMnemoCode() {
        return accountCurrencyMnemoCode;
    }

    public void setAccountCurrencyMnemoCode(String accountCurrencyMnemoCode) {
        this.accountCurrencyMnemoCode = accountCurrencyMnemoCode;
    }

    public String getAccountAgreementNumber() {
        return accountAgreementNumber;
    }

    public void setAccountAgreementNumber(String accountAgreementNumber) {
        this.accountAgreementNumber = accountAgreementNumber;
    }

    public String getAccountAgreementBankName() {
        return accountAgreementBankName;
    }

    public void setAccountAgreementBankName(String accountAgreementBankName) {
        this.accountAgreementBankName = accountAgreementBankName;
    }

    public String getAccountAgreementBankUNN() {
        return accountAgreementBankUNN;
    }

    public void setAccountAgreementBankUNN(String accountAgreementBankUNN) {
        this.accountAgreementBankUNN = accountAgreementBankUNN;
    }
}
