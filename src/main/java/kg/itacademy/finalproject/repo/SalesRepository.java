package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findAllByCompanyId(Long id);
}
