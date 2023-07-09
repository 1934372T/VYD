package com.nat.nat.entity;

import java.time.LocalDateTime;

import com.nat.nat.rules.Degree;
import com.nat.nat.rules.Grade;

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
    private String term;          // 年度
    private String degree;        // 学位
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
     * getter
     */

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

    public String getTerm() {
        return this.term;
    }

    public String getDegree() {
        return this.degree;
    }

    public String getNote() {
        return this.note;
    }


    /*
     * setter
     */

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setDegree(Grade grade) {
        this.degree = Degree.getDegree(grade);
    }

    public void setNote(String note) {
        this.note = note;
    }
}
