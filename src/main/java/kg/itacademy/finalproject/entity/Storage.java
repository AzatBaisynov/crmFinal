package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "p_storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage extends EntitySuperclass{
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "p_count", nullable = false)
    private Integer count;
    @Column(name = "unit_price", nullable = false)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @Column(name = "total_cost")
    private Integer cost;
}
