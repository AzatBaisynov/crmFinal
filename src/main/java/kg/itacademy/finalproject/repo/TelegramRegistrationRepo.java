package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.TelegramRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRegistrationRepo extends JpaRepository<TelegramRegistration, Long> {
    TelegramRegistration findByTelegramId(String id);
}
