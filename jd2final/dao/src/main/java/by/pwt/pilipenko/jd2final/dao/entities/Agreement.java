package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.AgreementVO;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "agreements")
public class Agreement extends AbstractEntity {

    private static final long serialVersionUID = -4425454817261521342L;
    private String number;
    private Date validFromDate;
    private Date validToDate;
    private Bank bank;
    private User client;

    private Set<Account> accounts;

    public Agreement() {
        super();
        accounts = new HashSet<Account>();
    }

    public Agreement(int id, String number, Date validFromDate, Date validToDate, Bank bank, User client) {
        super(id);
        this.number = number;
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
        this.bank = bank;
        this.client = client;

        accounts = new HashSet<Account>();

    }

    @Column(name = "number", columnDefinition = "VARCHAR2(45) NOT NULL UNIQUE")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "valid_from_date", nullable = false, length = 10)
    public Date getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
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
    @JoinColumn(name = "bank_id", nullable = false)
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((bank == null) ? 0 : bank.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((validFromDate == null) ? 0 : validFromDate.hashCode());
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
        Agreement other = (Agreement) obj;
        if (bank == null) {
            if (other.bank != null)
                return false;
        } else if (!bank.equals(other.bank))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (validFromDate == null) {
            if (other.validFromDate != null)
                return false;
        } else if (!validFromDate.equals(other.validFromDate))
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
        return "Agreement [id=" + getId() + ", number=" + number + ", validFromDate=" + validFromDate + ", validToDate="
                + validToDate + ", bank=" + bank.getName() + ", client=" + client.getLogin() + ", accounts=" + accounts + "]";
    }

    @OneToMany(mappedBy = "agreement", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccounts(Set<Account> accounts) {
        this.accounts.addAll(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> findAccounts(Account account) {
        List<Account> finded = new ArrayList<Account>();
        for (Account iaccount : accounts) {
            if ((account.getCurrency() != null && account.getCurrency().equals(iaccount.getCurrency())) && (account.getNumber() != null && account.getNumber().equals(iaccount.getNumber())))
                finded.add(iaccount);
        }
        return finded;
    }

    public AgreementVO createAgreementVO() {
        AgreementVO agreementVO = new AgreementVO();
        agreementVO.setId(getId());
        agreementVO.setNumber(number);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        agreementVO.setValidFromDate(df.format(validFromDate));
        agreementVO.setValidToDate(df.format(validToDate));
        agreementVO.setBankID(bank.getId());
        agreementVO.setBankName(bank.getName());
        agreementVO.setBankUNN(bank.getUNN());
        agreementVO.setUserID(client.getId());
        agreementVO.setUserFirstName(client.getFirstName());
        agreementVO.setUserLastName(client.getLastName());
        agreementVO.setUserPersonanumber(client.getPersonalNumber());
        return agreementVO;
    }

    public void deleteAccount(Account account) {
        accounts.remove(account);
    }

}
