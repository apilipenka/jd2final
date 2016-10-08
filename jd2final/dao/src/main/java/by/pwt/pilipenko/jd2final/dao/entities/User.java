package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.UserVO;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    private static final long serialVersionUID = 2450506525357642771L;
    private Set<Agreement> agreements;
    private String personalNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String login;
    private String password;
    private UserRole userRole;

    public User() {
        super();
        agreements = new HashSet<Agreement>();
    }

    public User(int id, String login, String firstName, String lastName, String password, String personalNumber,
                UserRole userRole, Date birthDate) {
        super(id);
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.personalNumber = personalNumber;
        this.userRole = userRole;
        this.birthDate = birthDate;

        agreements = new HashSet<Agreement>();

    }

    @Column(name = "login", columnDefinition = "NOT NULL")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "first_name", columnDefinition = "NOT NULL")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", columnDefinition = "NOT NULL")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", columnDefinition = "NOT NULL")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "personal_number", columnDefinition = "NOT NULL UNIQUE")
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role_id", nullable = false, updatable = false)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((personalNumber == null) ? 0 : personalNumber.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (personalNumber == null) {
            if (other.personalNumber != null) {
                return false;
            }
        } else if (!personalNumber.equals(other.personalNumber)) {
            return false;
        }

        if (userRole == null) {
            if (other.userRole != null) {
                return false;
            }
        } else if (!userRole.equals(other.userRole)) {
            return false;
        }

        if (birthDate == null) {
            if (other.birthDate != null) {
                return false;
            }
        } else if (!birthDate.equals(other.birthDate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", personalNumber=" + personalNumber + ", userRole=" + userRole + ", birthDate=" + birthDate + ", agreements=" + agreements + "]";
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false, length = 10)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public UserVO createUserVO() {
        UserVO userVO = new UserVO();

        userVO.setId(getId());
        userVO.setPersonalNumber(personalNumber);
        userVO.setFirstName(firstName);
        userVO.setLastName(lastName);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        userVO.setBirthDate(df.format(birthDate));

        userVO.setLogin(login);
        userVO.setPassword(password);
        userVO.setUserRoleID(userRole.getId());
        userVO.setUserRoleName(userRole.getName());

        return userVO;
    }

    public void deleteAgreement(Agreement agreement) {
        agreements.remove(agreement);
    }
}
