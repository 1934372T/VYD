package com.nat.nat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "presentations")
public class Presentation extends CommonEntity {

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

    public Presentation(int studentId, String title, LocalDateTime date, String note) {
        this.studentId = studentId;
        this.title = title;
        this.date = date;
        this.note = note;
    }

    /*
     * =====================================================
     */

    public int getStudentId() {
        return this.studentId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getPaperId() {
        return this.paperId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
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
