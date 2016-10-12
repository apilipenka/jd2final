package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class CommandVO extends EntityVO {
    private static final long serialVersionUID = -2656180060811361869L;
    private String commandp;
    private String url;
    private String label;
    private String comment;

    public CommandVO() {
        super();
    }

    public CommandVO(int id, String commandp, String url, String label, String comment, int userRoleId, String userRoleName) {
        super(id);
        this.commandp = commandp;
        this.url = url;
        this.label = label;
        this.comment = comment;
    }

    public String getCommandp() {
        return commandp;
    }

    public void setCommandp(String commandp) {
        this.commandp = commandp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
