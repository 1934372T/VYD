package com.nat.nat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "slides")
public class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdAt; // 作成日時
    private LocalDateTime updatedAt; // 更新日時
    private LocalDateTime deletedAt; // 削除日時
    private String name;

    @Lob
    private byte[] body;

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

    public int getId() {
        return this.id;
    }

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

    public String getName() {
        return this.name;
    }

    public byte[] getBody() {
        return this.body;
    }
}
