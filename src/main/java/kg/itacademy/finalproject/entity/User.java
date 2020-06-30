package kg.itacademy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntitySuperclass{
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "user_full_name")
    private String userFullName;
    @Column(name = "age", nullable = false)
    private LocalDate age;
    @Column(name = "position", nullable = false)
    private String position;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "telegram_key")
    private String telegramKey;
    @Column(name = "status")
    private Integer status;
}
