package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company extends EntitySuperclass{
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "cash")
    private Integer cash;
}
