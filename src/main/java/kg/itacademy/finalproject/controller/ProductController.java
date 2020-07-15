package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Image;
import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.model.ProductsModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.service.ImageService;
import kg.itacademy.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductsModel productsModel, Principal principal) {
        Product product = productService.createByModel(principal.getName(),productsModel);
        if(product == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create/new")
    public Image create(@RequestParam(name = "image") MultipartFile multipartFile,
                        @RequestParam(name = "productName") String name,
                        Principal principal) {
        ProductsModel pM = new ProductsModel();
        pM.setProductName(name);
        String id = productService.createByModel(principal.getName(),pM).getId() + "";
        return imageService.saveLocalPath(multipartFile,id, principal.getName());
    }
}
