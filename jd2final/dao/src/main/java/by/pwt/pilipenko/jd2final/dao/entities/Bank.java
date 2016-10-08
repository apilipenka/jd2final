package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.BankVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "banks")
public class Bank extends AbstractEntity {
    private static final long serialVersionUID = 4713651234377737733L;
    private Set<Agreement> agreements;
    private String UNN;
    private String name;

    public Bank() {
        super();
        agreements = new HashSet<Agreement>();
    }

    public Bank(int id, String name, String UNN) {
        super(id);
        this.name = name;
        this.UNN = UNN;
        agreements = new HashSet<Agreement>();
    }

    @Column(name = "name", unique = false, nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((UNN == null) ? 0 : UNN.hashCode());
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
        Bank other = (Bank) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        if (UNN == null) {
            if (other.UNN != null)
                return false;
        } else if (!UNN.equals(other.UNN))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Bank [id=" + getId() + ", name=" + name + ", UNN=" + UNN + "]";
    }

    @Column(name = "unn", unique = true, nullable = false, length = 45)
    public String getUNN() {
        return UNN;
    }

    public void setUNN(String UNN) {
        this.UNN = UNN;
    }


    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(Set<Agreement> agreements) {
        this.agreements = agreements;
    }

    public void addAgreements(Set<Agreement> agreements) {
        this.agreements.addAll(agreements);
    }

    public List<Agreement> findAccounts(Agreement agreement) {
        List<Agreement> finded = new ArrayList<Agreement>();
        for (Agreement iagreement : agreements) {
            if ((agreement.getNumber() != null && agreement.getNumber().equals(iagreement.getNumber())))
                finded.add(iagreement);
        }
        return finded;
    }

    public BankVO createBankVO() {
        BankVO bankVO = new BankVO();
        bankVO.setId(getId());
        bankVO.setUNN(UNN);
        bankVO.setName(name);
        return bankVO;
    }

    public void deleteAgreement(Agreement agreement) {
        agreements.remove(agreement);
    }

}
