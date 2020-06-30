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
public class TelegramRegistration extends EntitySuperclass{
    @Column(name = "special_key", nullable = false)
    private String specialKey;
    @Column(name = "telegram_id", nullable = false, unique = true)
    private String telegramId;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
