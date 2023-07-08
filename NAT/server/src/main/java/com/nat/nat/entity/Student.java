package com.nat.nat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdAt; // 作成日時
    private LocalDateTime updatedAt; // 更新日時
    private LocalDateTime deletedAt; // 削除日時
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

    public int getId() {
        return this.id;
    }

    /*
     * DO NOT SUPPORT ID_SETTER
     */

    /*
     * 作成日時
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /*
     * 更新日時
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /*
     * 削除日時
     */
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

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

