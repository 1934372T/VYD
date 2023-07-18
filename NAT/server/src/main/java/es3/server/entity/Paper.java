package es3.server.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "papers")
public class Paper extends Common {
    @Lob
    private byte[] body;
    private String name;

    protected Paper() {}

    public Paper(Long id) {
        this.id = id;
    }

    public Paper(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }
}
