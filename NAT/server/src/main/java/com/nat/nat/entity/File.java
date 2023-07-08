package com.nat.nat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentId;
    private String term;

    protected File() {}

    public File(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public String getTerm() {
        return this.term;
    }
}
