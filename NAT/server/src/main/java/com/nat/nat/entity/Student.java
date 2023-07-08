package com.nat.nat.entity;

import com.nat.nat.rules.Grade;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends CommonEntity {

    private String firstName; // 名前
    private String lastName; // 名字
    @Enumerated(EnumType.STRING)
    private Grade grade; // 学年
    private String studentId; // 学籍番号
    private String password;

    /*
     * =====================================================
     */

    protected Student() {}

    public Student(String firstName, String lastName, Grade grade, String studentId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
        this.password = password;
    }

    public Student(int id, String firstName, String lastName, Grade grade, String studentId, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
        this.password = password;
    }

    /*
     * =====================================================
     */

    public Grade getGrade() {
        return this.grade;
    }

    public String getStudnetId() {
        return this.studentId;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }
}

