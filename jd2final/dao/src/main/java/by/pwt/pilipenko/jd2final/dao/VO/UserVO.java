package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class UserVO extends EntityVO {
    private static final long serialVersionUID = -7212663851796971517L;
    private String personalNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String login;
    private String password;
    private int userRoleID;
    private String userRoleName;

    public UserVO() {
        super();
    }

    public UserVO(int id, String personalNumber, String firstName, String lastName, String birthDate, String login, String password, int userRoleID, String userRoleName) {
        super(id);
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.userRoleID = userRoleID;
        this.userRoleName = userRoleName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getDescription() {
        return personalNumber + ' ' + firstName + ' ' + lastName;
    }

}
