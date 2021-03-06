package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sales extends EntitySuperclass{
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
