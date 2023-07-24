package com.nat.nat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "slides")
public class Slide extends CommonEntity {

    private byte[] body;
    private String name;

    /*
     * =====================================================
     */

    protected Slide() {}

    public Slide(int id) {
        this.id = id;
    }

    public Slide(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }
    
    /*
     * =====================================================
     */

    public String getName() {
        return this.name;
    }

    public byte[] getBody() {
        return this.body;
    }
}
