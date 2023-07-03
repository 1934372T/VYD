package com.nat.nat.domain;

public class Student {
    private int id;
    private String firstName; // 名前
    private String lastName; // 名字
    private String grade; // 学年
    private String studentId; // 学籍番号

    public Student() {}

    public Student(String firstName, String lastName, String grade, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
    }

    public Student(int id, String firstName, String lastName, String grade, String studentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }

    public int getId() {
        return this.id;
    }

    /*
     * DO NOT SUPPORT ID_SETTER
     */

    public String getGrade() {
        return this.grade;
    }

    public String getStudnetId() {
        return this.studentId;
    }
}
