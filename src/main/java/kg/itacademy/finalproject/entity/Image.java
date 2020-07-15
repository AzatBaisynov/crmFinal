package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image extends EntitySuperclass{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "path")
    private String path;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
