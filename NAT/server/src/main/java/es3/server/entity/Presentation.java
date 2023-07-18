package es3.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "presentations")
public class Presentation extends Common {
    @Column(name = "student_id")
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "paper_id")
    private Long paperId;

    @OneToOne
    @JoinColumn(name = "paper_id", insertable = false, updatable = false)
    private Paper paper;

    @Column(name = "slid_id")
    private Long slideId;

    @OneToOne
    @JoinColumn(name = "slide_id", insertable = false, updatable = false)
    private Slide slide;

    private String          title;      // 発表タイトル
    private LocalDateTime   date;       // 発表日
    private String          term;       // 年度
    private String          degree;     // 学位
    private String          note;       // 備考

    protected Presentation() {}

    public Presentation(Long studentId, String title, LocalDateTime date, String note) {
        this.studentId = studentId;
        this.title = title;
        this.date = date;
        this.note = note;
    }
}
