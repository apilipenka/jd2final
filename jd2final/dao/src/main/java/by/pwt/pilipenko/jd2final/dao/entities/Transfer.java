package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.TransferVO;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transfer")
public class Transfer extends AbstractEntity {


    private static final long serialVersionUID = 3297838146418817228L;

    private Account creditAccount;
    private Account debitAccount;
    private double amount;
    private Date transferDate;
    private String comment;

    public Transfer() {

    }

    public Transfer(int id, Account creditAccount, Account debetAccount, double amount, Date transferDate, String comment) {
        super(id);
        this.creditAccount = creditAccount;
        this.debitAccount = debetAccount;
        this.amount = amount;
        this.transferDate = transferDate;
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_account_id", nullable = false, updatable = false)
    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debit_account_id", nullable = false, updatable = false)
    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "transfer_date", nullable = false, length = 10)
    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Column(name = "comment", columnDefinition = "VARCHAR2(200)")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Transfer transfer = (Transfer) o;

        if (Double.compare(transfer.amount, amount) != 0) return false;
        if (!creditAccount.equals(transfer.creditAccount)) return false;
        if (!debitAccount.equals(transfer.debitAccount)) return false;
        if (!transferDate.equals(transfer.transferDate)) return false;
        return comment.equals(transfer.comment);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + creditAccount.hashCode();
        result = 31 * result + debitAccount.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + transferDate.hashCode();
        result = 31 * result + comment.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                ", amount=" + amount +
                ", transferDate=" + transferDate +
                ", comment='" + comment + '\'' +
                "} " + super.toString();
    }

    public TransferVO createTransferVO() {

        TransferVO transferVO = new TransferVO();
        transferVO.setId(getId());

        transferVO.setDebitAccountID(debitAccount.getId());
        transferVO.setDebitAccountNumber(debitAccount.getNumber());
        transferVO.setDebitAccountAgreementId(debitAccount.getAgreement().getId());
        transferVO.setDebitAccountAgreementNumber(debitAccount.getAgreement().getNumber());
        transferVO.setDebitAccountAgreementBankId(debitAccount.getAgreement().getBank().getId());
        transferVO.setDebitAccountAgreementBankName(debitAccount.getAgreement().getBank().getName());
        transferVO.setDebitAccountAgreementBankUNN(debitAccount.getAgreement().getBank().getUNN());
        transferVO.setDebitAccountCurrencyId(debitAccount.getCurrency().getId());
        transferVO.setDebitAccountCurrencyMnemoCode(debitAccount.getCurrency().getMnemoCode());

        transferVO.setCreditAccountID(creditAccount.getId());
        transferVO.setCreditAccountNumber(creditAccount.getNumber());
        transferVO.setCreditAccountAgreementId(creditAccount.getAgreement().getId());
        transferVO.setCreditAccountAgreementNumber(creditAccount.getAgreement().getNumber());
        transferVO.setCreditAccountAgreementBankId(creditAccount.getAgreement().getBank().getId());
        transferVO.setCreditAccountAgreementBankName(creditAccount.getAgreement().getBank().getName());
        transferVO.setCreditAccountAgreementBankUNN(creditAccount.getAgreement().getBank().getUNN());
        transferVO.setCreditAccountCurrencyId(creditAccount.getCurrency().getId());
        transferVO.setCreditAccountCurrencyMnemoCode(creditAccount.getCurrency().getMnemoCode());

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        transferVO.setTransferDate(df.format(transferDate));
        transferVO.setAmount(amount);
        transferVO.setComment(comment);


        return transferVO;

    }


}
