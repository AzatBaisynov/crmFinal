package kg.itacademy.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends EntitySuperclass {
    @Column(name = "product_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
