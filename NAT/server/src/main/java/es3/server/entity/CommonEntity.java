package es3.server.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long          id;         // ID
    protected LocalDateTime createdAt;  // 作成日時
    protected LocalDateTime updatedAt;  // 更新日時
    protected LocalDateTime deletedAt;  // 削除日時
}
