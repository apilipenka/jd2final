package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/22/2016.
 */
public class UserRoleCommandVO extends EntityVO {

    private static final long serialVersionUID = 5579266712234784938L;
    private int userRoleID;
    private String userRoleName;
    private int commandID;
    private String commandComment;

    public UserRoleCommandVO() {
    }

    public UserRoleCommandVO(int userRoleID, String userRoleName, int commandID, String commandComment) {
        this.userRoleID = userRoleID;
        this.userRoleName = userRoleName;
        this.commandID = commandID;
        this.commandComment = commandComment;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getCommandID() {
        return commandID;
    }

    public void setCommandID(int commandID) {
        this.commandID = commandID;
    }

    public String getCommandComment() {
        return commandComment;
    }

    public void setCommandComment(String commandComment) {
        this.commandComment = commandComment;
    }
}
