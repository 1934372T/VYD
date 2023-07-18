package es3.server.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import es3.server.rules.Grade;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends Common {
    @Enumerated(EnumType.STRING)
    private Grade   grade;      // 学年
    private String  firstName;  // 名前
    private String  lastName;   // 名字
    private String  studentId;  // 学籍番号
    private String  password;   // パスワード

    protected Student() {}

    public Student(String firstName, String lastName, Grade grade, String studentId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
        this.password = password;
    }

    public Student(Long id, String firstName, String lastName, Grade grade, String studentId, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = studentId;
        this.password = password;
    }
}
