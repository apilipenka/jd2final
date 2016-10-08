package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class TransferVO extends EntityVO {

    private static final long serialVersionUID = 5021236027609577714L;

    private int debitAccountID;
    private String debitAccountNumber;
    private int debitAccountAgreementId;
    private String debitAccountAgreementNumber;
    private int debitAccountAgreementBankId;
    private String debitAccountAgreementBankName;
    private String debitAccountAgreementBankUNN;
    private int debitAccountCurrencyId;
    private String debitAccountCurrencyMnemoCode;

    private int creditAccountID;
    private String creditAccountNumber;
    private int creditAccountAgreementId;
    private String creditAccountAgreementNumber;
    private int creditAccountAgreementBankId;
    private String creditAccountAgreementBankName;
    private String creditAccountAgreementBankUNN;
    private int creditAccountCurrencyId;
    private String creditAccountCurrencyMnemoCode;

    private String transferDate;
    private double amount;
    private String comment;

    public TransferVO() {
    }

    public TransferVO(int id, int debitAccountID, String debitAccountNumber, int debitAccountAgreementId, String debitAccountAgreementNumber, int debitAccountAgreementBankId, String debitAccountAgreementBankName, String debitAccountAgreementBankUNN, int debitAccountCurrencyId, String debitAccountCurrencyMnemoCode, int creditAccountID, String creditAccountNumber, int creditAccountAgreementId, String creditAccountAgreementNumber, int creditAccountAgreementBankId, String creditAccountAgreementBankName, String creditAccountAgreementBankUNN, int creditAccountCurrencyId, String creditAccountCurrencyMnemoCode, String transferDate, double amount, String comment) {
        super(id);
        this.debitAccountID = debitAccountID;
        this.debitAccountNumber = debitAccountNumber;
        this.debitAccountAgreementId = debitAccountAgreementId;
        this.debitAccountAgreementNumber = debitAccountAgreementNumber;
        this.debitAccountAgreementBankId = debitAccountAgreementBankId;
        this.debitAccountAgreementBankName = debitAccountAgreementBankName;
        this.debitAccountAgreementBankUNN = debitAccountAgreementBankUNN;
        this.debitAccountCurrencyId = debitAccountCurrencyId;
        this.debitAccountCurrencyMnemoCode = debitAccountCurrencyMnemoCode;
        this.creditAccountID = creditAccountID;
        this.creditAccountNumber = creditAccountNumber;
        this.creditAccountAgreementId = creditAccountAgreementId;
        this.creditAccountAgreementNumber = creditAccountAgreementNumber;
        this.creditAccountAgreementBankId = creditAccountAgreementBankId;
        this.creditAccountAgreementBankName = creditAccountAgreementBankName;
        this.creditAccountAgreementBankUNN = creditAccountAgreementBankUNN;
        this.creditAccountCurrencyId = creditAccountCurrencyId;
        this.creditAccountCurrencyMnemoCode = creditAccountCurrencyMnemoCode;
        this.transferDate = transferDate;
        this.amount = amount;
        this.comment = comment;
    }

    public int getDebitAccountID() {
        return debitAccountID;
    }

    public void setDebitAccountID(int debitAccountID) {
        this.debitAccountID = debitAccountID;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public int getDebitAccountAgreementId() {
        return debitAccountAgreementId;
    }

    public void setDebitAccountAgreementId(int debitAccountAgreementId) {
        this.debitAccountAgreementId = debitAccountAgreementId;
    }

    public String getDebitAccountAgreementNumber() {
        return debitAccountAgreementNumber;
    }

    public void setDebitAccountAgreementNumber(String debitAccountAgreementNumber) {
        this.debitAccountAgreementNumber = debitAccountAgreementNumber;
    }

    public int getDebitAccountAgreementBankId() {
        return debitAccountAgreementBankId;
    }

    public void setDebitAccountAgreementBankId(int debitAccountAgreementBankId) {
        this.debitAccountAgreementBankId = debitAccountAgreementBankId;
    }

    public String getDebitAccountAgreementBankName() {
        return debitAccountAgreementBankName;
    }

    public void setDebitAccountAgreementBankName(String debitAccountAgreementBankName) {
        this.debitAccountAgreementBankName = debitAccountAgreementBankName;
    }

    public String getDebitAccountAgreementBankUNN() {
        return debitAccountAgreementBankUNN;
    }

    public void setDebitAccountAgreementBankUNN(String debitAccountAgreementBankUNN) {
        this.debitAccountAgreementBankUNN = debitAccountAgreementBankUNN;
    }

    public int getDebitAccountCurrencyId() {
        return debitAccountCurrencyId;
    }

    public void setDebitAccountCurrencyId(int debitAccountCurrencyId) {
        this.debitAccountCurrencyId = debitAccountCurrencyId;
    }

    public String getDebitAccountCurrencyMnemoCode() {
        return debitAccountCurrencyMnemoCode;
    }

    public void setDebitAccountCurrencyMnemoCode(String debitAccountCurrencyMnemoCode) {
        this.debitAccountCurrencyMnemoCode = debitAccountCurrencyMnemoCode;
    }

    public int getCreditAccountID() {
        return creditAccountID;
    }

    public void setCreditAccountID(int creditAccountID) {
        this.creditAccountID = creditAccountID;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public int getCreditAccountAgreementId() {
        return creditAccountAgreementId;
    }

    public void setCreditAccountAgreementId(int creditAccountAgreementId) {
        this.creditAccountAgreementId = creditAccountAgreementId;
    }

    public String getCreditAccountAgreementNumber() {
        return creditAccountAgreementNumber;
    }

    public void setCreditAccountAgreementNumber(String creditAccountAgreementNumber) {
        this.creditAccountAgreementNumber = creditAccountAgreementNumber;
    }

    public int getCreditAccountAgreementBankId() {
        return creditAccountAgreementBankId;
    }

    public void setCreditAccountAgreementBankId(int creditAccountAgreementBankId) {
        this.creditAccountAgreementBankId = creditAccountAgreementBankId;
    }

    public String getCreditAccountAgreementBankName() {
        return creditAccountAgreementBankName;
    }

    public void setCreditAccountAgreementBankName(String creditAccountAgreementBankName) {
        this.creditAccountAgreementBankName = creditAccountAgreementBankName;
    }

    public String getCreditAccountAgreementBankUNN() {
        return creditAccountAgreementBankUNN;
    }

    public void setCreditAccountAgreementBankUNN(String creditAccountAgreementBankUNN) {
        this.creditAccountAgreementBankUNN = creditAccountAgreementBankUNN;
    }

    public int getCreditAccountCurrencyId() {
        return creditAccountCurrencyId;
    }

    public void setCreditAccountCurrencyId(int creditAccountCurrencyId) {
        this.creditAccountCurrencyId = creditAccountCurrencyId;
    }

    public String getCreditAccountCurrencyMnemoCode() {
        return creditAccountCurrencyMnemoCode;
    }

    public void setCreditAccountCurrencyMnemoCode(String creditAccountCurrencyMnemoCode) {
        this.creditAccountCurrencyMnemoCode = creditAccountCurrencyMnemoCode;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




}
