package by.pwt.pilipenko.jd2final.dao.entities;


import by.pwt.pilipenko.jd2final.dao.VO.UserRoleCommandVO;

/**
 * Created by apilipenka on 8/22/2016.
 */
public class UserRoleCommand extends AbstractEntity {
    private static final long serialVersionUID = 5754335739390567505L;
    private UserRole userRole;
    private Command command;

    public UserRoleCommand() {
    }

    public UserRoleCommand(int id, UserRole userRole, Command command) {
        super(id);
        this.userRole = userRole;
        this.command = command;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserRoleCommand that = (UserRoleCommand) o;

        if (!userRole.equals(that.userRole)) return false;
        return command.equals(that.command);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userRole.hashCode();
        result = 31 * result + command.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRoleCommand{" +
                "userRole=" + userRole +
                ", command=" + command +
                '}';
    }

    public UserRoleCommandVO createUserRoleCommandVO() {
        UserRoleCommandVO userRoleCommandVO = new UserRoleCommandVO();

        userRoleCommandVO.setId(getId());
        userRoleCommandVO.setUserRoleID(userRole.getId());
        userRoleCommandVO.setUserRoleName(userRole.getName());
        userRoleCommandVO.setCommandID(command.getId());
        userRoleCommandVO.setCommandComment(command.getComment());

        return userRoleCommandVO;
    }


}
