package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Image;
import kg.itacademy.finalproject.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public Image saveLocalPath(MultipartFile image, String id, String login) {
        if (productService.getById(Long.parseLong(id)).getCompany().getId() == userService.getByLogin(login).getCompany().getId()) {
            String name = System.currentTimeMillis() + image.getOriginalFilename();
            String pathToPhoto = "C:/Users/Азат/Desktop/frontFinal/easystoragefront/src/Pages/images/" + name;
            Long pId = Long.parseLong(id);
            File file = new File(pathToPhoto);
            try {
                image.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image1 = new Image();
            image1.setName(name);
            image1.setPath(pathToPhoto);
            image1.setProduct(productService.getById(pId));
            return imageRepository.save(image1);
        }
        return null;
    }

    @Override
    public String getImagePath(String nameProduct, Long companyId) {
        Long pId = productService.getByNameAndCompanyId(nameProduct, companyId).getId();
        Image image = imageRepository.getByProduct_Id(pId);
        if(image == null) {
            return "./images/photo.jpg";
        }
        String path = image.getPath();
        System.out.println(path);
        path = path.replace("C:/Users/Азат/Desktop/frontFinal/easystoragefront/src/Pages", ".");
        System.out.println(path);
        return path;
    }


    @Override
    public Image getById(Long id) {
        return imageRepository.getOne(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

}
