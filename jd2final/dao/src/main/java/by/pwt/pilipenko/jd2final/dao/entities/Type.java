package by.pwt.pilipenko.jd2final.dao.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Type extends AbstractEntity {
    private static final long serialVersionUID = 3392012103334697756L;
    private String name;
    private String description;

    public Type() {
        super();
    }

    public Type(int id, String name, String dsecription) {
        super(id);
        this.name = name;
        this.description = dsecription;
    }

    @Column(name = "name", columnDefinition = "NOT NULL UNIQUE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Type other = (Type) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Type [id=" + getId() + ", name=" + name + ", description=" + description + "]";

    }

}
