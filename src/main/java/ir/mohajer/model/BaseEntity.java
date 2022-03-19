package ir.mohajer.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    private long id;
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
