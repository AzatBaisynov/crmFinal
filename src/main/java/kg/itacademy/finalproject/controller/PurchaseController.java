package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Purchase;
import kg.itacademy.finalproject.model.PurchaseReturn;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.service.ProductService;
import kg.itacademy.finalproject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Purchase> getAll() {
        return purchaseService.getAll();
    }

    @GetMapping("/{id}")
    public Purchase getById(@PathVariable Long id){
        return purchaseService.getById(id);
    }

    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        return purchaseService.create(purchase);
    }

    @GetMapping("/list")
    public PurchaseReturn getFullList(@RequestParam String date, Principal principal){
        LocalDate localDate = LocalDate.parse(date);
        List<PurchaseSalesProductsListModel> fullList = purchaseService.getFullList(principal.getName(), localDate);
        List<String> products = productService.getProductNames(principal.getName());
        PurchaseReturn pR = new PurchaseReturn(fullList, products);

        return pR;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createByModel(@RequestBody PurchaseSalesCreateModel pSCM, Principal principal){
        String response = purchaseService.createByModel(principal.getName(), pSCM);
        if(response == "Created") return new ResponseEntity<>("Purchase created!", HttpStatus.OK);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
