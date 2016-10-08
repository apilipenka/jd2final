package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.CardVO;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "cards")
public class Card extends AbstractEntity {

    private static final long serialVersionUID = 7078093543381864816L;
    private String number;
    private String name;
    private Date validToDate;
    private Account account;

    public Card() {
        super();
    }

    public Card(int id, String number, String name, Date validToDate, Account account) {
        super(id);
        this.number = number;
        this.name = name;
        this.validToDate = validToDate;
        this.account = account;
    }

    @Column(name = "number", columnDefinition = "VARCHAR2(45) NOT NULL UNIQUE")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "name", columnDefinition = "VARCHAR2(100) NOT NULL")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "valid_to_date", nullable = false, length = 10)
    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }

    @ManyToOne
    @JoinColumn(name = "accounts_id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((validToDate == null) ? 0 : validToDate.hashCode());
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
        Card other = (Card) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (validToDate == null) {
            if (other.validToDate != null)
                return false;
        } else if (!validToDate.equals(other.validToDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [id=" + getId() + ", number=" + number + ", name=" + name + ", validToDate=" + validToDate
                + ", account=" + account + "]";
    }

    public CardVO createCardVO() {

        CardVO cardVO = new CardVO();
        cardVO.setId(getId());
        cardVO.setNumber(number);
        cardVO.setName(name);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        cardVO.setValidToDate(df.format(validToDate));

        cardVO.setAccountID(account.getId());
        cardVO.setAccountNumber(account.getNumber());
        cardVO.setAccountAgreementId(account.getAgreement().getId());
        cardVO.setAccountAgreementNumber(account.getAgreement().getNumber());
        cardVO.setAccountAgreementBankId(account.getAgreement().getBank().getId());
        cardVO.setAccountAgreementBankName(account.getAgreement().getBank().getName());
        cardVO.setAccountAgreementBankUNN(account.getAgreement().getBank().getUNN());
        cardVO.setAccountCurrencyId(account.getCurrency().getId());
        cardVO.setAccountCurrencyMnemoCode(account.getCurrency().getMnemoCode());
        return cardVO;

    }
}
