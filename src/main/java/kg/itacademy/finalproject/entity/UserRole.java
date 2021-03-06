package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends EntitySuperclass {
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
