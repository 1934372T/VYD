package es3.server.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "presentations")
public class Presentation extends Common {
    private Long            studentId;  // 学生のID (学籍番号ではない)
    private Long            paperId;    // 論文ID
    private Long            slideId;    // スライドID
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
