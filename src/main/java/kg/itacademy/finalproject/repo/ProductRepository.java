package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByCompanyId(Long id);
    Product findByNameAndCompany_Id(String name, Long id);
    Product getByName(String name);
}
