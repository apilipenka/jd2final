package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class AgreementVO extends EntityVO {
    private static final long serialVersionUID = 336493084896754493L;
    private String number;
    private String validFromDate;
    private String validToDate;
    private int bankID;
    private String bankName;
    private String bankUNN;
    private int userID;
    private String userFirstName;
    private String userLastName;
    private String userPersonanumber;

    public AgreementVO() {
        super();
    }

    public AgreementVO(int id, String number, String validFromDate, String validToDate, int bankID, String bankName, String bankUNN, int userID, String userFirstName, String userLastName, String userPersonanumber) {
        super(id);
        this.number = number;
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
        this.bankID = bankID;
        this.bankName = bankName;
        this.bankUNN = bankUNN;
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPersonanumber = userPersonanumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(String validFromDate) {
        this.validFromDate = validFromDate;
    }

    public String getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUNN() {
        return bankUNN;
    }

    public void setBankUNN(String bankUNN) {
        this.bankUNN = bankUNN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPersonanumber() {
        return userPersonanumber;
    }

    public void setUserPersonanumber(String userPersonanumber) {
        this.userPersonanumber = userPersonanumber;
    }

    public String getDescription() {
        return number + ' ' + bankName;
    }

}
