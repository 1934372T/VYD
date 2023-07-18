package es3.server.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@MappedSuperclass
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long          id;        // ID
    protected LocalDateTime createdAt; // 作成日時
    protected LocalDateTime updatedAt; // 更新日時
    protected LocalDateTime deletedAt; // 削除日時

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }

    @PreRemove
    public void onPreRemove() {
        setDeletedAt(LocalDateTime.now());
    }
}
