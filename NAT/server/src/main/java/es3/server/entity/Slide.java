package es3.server.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "slides")
public class Slide extends Common {

    private byte[] body;
    private String name;

    protected Slide() {}

    public Slide(int id) {
        this.id = id;
    }

    public Slide(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }
}
