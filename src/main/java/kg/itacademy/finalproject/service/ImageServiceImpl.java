package kg.itacademy.finalproject.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.itacademy.finalproject.entity.Image;
import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductService productService;

//    @Override
//    public Image create(MultipartFile image, String id) {
//        Image image1 = new Image();
//        Long idL = Long.parseLong(id);
//        Product product = productService.getById(idL);
//
//        File f;
//        String pathToPhoto = "C:\\Users\\Азат\\Desktop\\photos\\" + System.currentTimeMillis() +  image.getOriginalFilename();
//        File file = new File(pathToPhoto);
//        try {
//            image.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        final String cloudinaryUrl = "cloudinary://757921463476931:qltwJ0k6AFMBAgAlSZBWa9sw9CU@dvfikvh7a";
//        try {
//            int ends;
//            if (image.getOriginalFilename().contains(".jpg")) ends = 4;
//            else if (image.getOriginalFilename().contains(".jpeg")) ends = 5;
//            else ends = 4;
//            f = Files.createTempFile(System.currentTimeMillis() + "",
//                    image.getOriginalFilename().substring(image.getOriginalFilename().length() - ends)).toFile();
//            image.transferTo(f);
//            Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
//            Map uploadResult = cloudinary.uploader().upload(f, ObjectUtils.emptyMap());
//            image1.setName((String) uploadResult.get("public_id"));
//            image1.setUrl((String) uploadResult.get("url"));
//            image1.setFormat((String) uploadResult.get("format"));
//            image1.setProduct(product);
//            Image save = imageRepository.save(image1);
//            return saveLocalPath(image, save);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//
//    }


    @Override
    public Image saveLocalPath(MultipartFile image, String id, String login) {
        String name = System.currentTimeMillis() +  image.getOriginalFilename();
        String pathToPhoto = "C:\\Users\\Азат\\Desktop\\photos\\" + name;
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


    @Override
    public Image getById(Long id) {
        return imageRepository.getOne(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

}
