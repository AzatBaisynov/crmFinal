package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String string);
    List<User> findAllByCompanyId(Long id);
    User findByTelegramKey(String key);
    User getByLogin(String string);
}
