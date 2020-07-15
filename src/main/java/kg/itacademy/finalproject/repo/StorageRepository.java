package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findAllByCompanyIdAndCountGreaterThan(Long id, Integer num);
    Storage findByProduct(Product product);
    List<Storage> findAllByCompanyId(Long id);
}
