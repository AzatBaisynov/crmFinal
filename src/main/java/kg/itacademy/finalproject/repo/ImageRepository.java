package kg.itacademy.finalproject.repo;

import kg.itacademy.finalproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getByProduct_Id(Long id);
}
