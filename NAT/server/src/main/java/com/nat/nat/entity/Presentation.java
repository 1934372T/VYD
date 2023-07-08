package com.nat.nat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "presentations")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdAt; // 作成日時
    private LocalDateTime updatedAt; // 更新日時
    private LocalDateTime deletedAt; // 削除日時
    private int studentId;        // 学生のID（学籍番号ではない）
    private int paperId;          // 論文ID（ファイルID）
    private int slideId;          // スライドID（ファイル）
    private String title;         // 発表タイトル
    private LocalDateTime date;   // 発表日
    private String note;          // 備考

    /*
     * =====================================================
     */

    protected Presentation() {}

    /*
     * =====================================================
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

    public int getStudentId() {
        return this.studentId;
    }

    public int getPaperId() {
        return this.paperId;
    }

    public int getSlideId() {
        return this.slideId;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getNote() {
        return this.note;
    }
}
