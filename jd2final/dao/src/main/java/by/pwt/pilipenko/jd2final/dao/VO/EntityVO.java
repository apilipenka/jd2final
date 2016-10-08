package by.pwt.pilipenko.jd2final.dao.VO;

import java.io.Serializable;

/**
 * Created by apilipenka on 8/5/2016.
 */
public abstract class EntityVO implements Serializable {
    private static final long serialVersionUID = 341009622048171603L;
    int id;

    public EntityVO() {
        super();
    }

    public EntityVO(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
