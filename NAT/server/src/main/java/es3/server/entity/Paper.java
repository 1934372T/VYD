package es3.server.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "papers")
public class Paper extends CommonEntity {
    
}
