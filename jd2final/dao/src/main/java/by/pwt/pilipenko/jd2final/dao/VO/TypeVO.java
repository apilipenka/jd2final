package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class TypeVO extends EntityVO {

    private static final long serialVersionUID = -8470350061648533311L;
    private String name;
    private String description;

    public TypeVO() {
        super();
    }

    public TypeVO(int id, String name, String dsecription) {
        super(id);
        this.name = name;
        this.description = dsecription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
