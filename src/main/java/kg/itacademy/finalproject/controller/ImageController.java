package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Image;
import kg.itacademy.finalproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAll() {
        return imageService.getAll();
    }

    @GetMapping("/{id}")
    public Image getById(@PathVariable Long id){
        return imageService.getById(id);
    }

    @PostMapping
    public Image create(@RequestParam(name = "image") MultipartFile multipartFile,
                        @RequestParam(name = "productId") String id,
                        Principal principal) {
        return imageService.saveLocalPath(multipartFile,id, principal.getName());
    }
}
