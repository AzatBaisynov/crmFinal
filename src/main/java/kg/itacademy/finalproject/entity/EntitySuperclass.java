package kg.itacademy.finalproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class EntitySuperclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @PrePersist
    public void persistCreate(){
        this.dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    public void persistUpdate(){
        this.dateUpdated = LocalDateTime.now();
    }
}
