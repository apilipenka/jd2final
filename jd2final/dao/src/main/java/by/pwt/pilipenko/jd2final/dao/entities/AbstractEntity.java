package by.pwt.pilipenko.jd2final.dao.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -3079361923938103521L;
    private int id;

    public AbstractEntity() {
        super();
    }

    public AbstractEntity(int id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER(11) UNIQUE")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        //result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "AbstractEntity [id=" + id + "]";
    }

}
