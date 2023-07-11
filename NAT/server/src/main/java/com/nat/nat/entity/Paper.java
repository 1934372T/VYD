package com.nat.nat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "papers")
public class Paper extends CommonEntity {

    @Lob
    private byte[] body;
    private String name;


    /*
     * =====================================================
     */

    protected Paper() {}

    public Paper(int id) {
        this.id = id;
    }

    public Paper(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }

    /*
     * =====================================================
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

}
