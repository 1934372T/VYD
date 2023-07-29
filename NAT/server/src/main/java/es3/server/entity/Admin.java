package es3.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
public class Admin extends Common {
    @Enumerated(EnumType.STRING)
    private String  firstName;  // 名前
    private String  lastName;   // 名字
    @Column(unique = true)
    private String  email;      // メールアドレス
    private String  password;   // パスワード

    public Admin() {}

    public Admin(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Admin(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName;
    }
}
