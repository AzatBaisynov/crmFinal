package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.model.MainPageModel;
import kg.itacademy.finalproject.model.ProductsModel;
import kg.itacademy.finalproject.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/main")
public class MainPageController {
    @Autowired
    private MainPageService mainPageService;

    @GetMapping
    public ResponseEntity<MainPageModel> getMainPage(@RequestParam String date, Principal principal){
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(date);
        System.out.println(localDate);
        System.out.println(principal.getName());
        MainPageModel model = mainPageService.getMainPage(principal.getName(), localDate);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/products")
    public List<ProductsModel> getProducts(Principal principal){
        List<ProductsModel> list = mainPageService.getProducts(principal.getName());
        return list;
    }
}
