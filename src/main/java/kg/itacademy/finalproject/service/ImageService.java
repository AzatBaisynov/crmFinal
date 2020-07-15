package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
//    Image create(MultipartFile image, String id);
    Image getById(Long id);
    List<Image> getAll();
    Image saveLocalPath(MultipartFile image, String id, String login);
    String getImagePath(String nameProduct, Long companyId);
}
