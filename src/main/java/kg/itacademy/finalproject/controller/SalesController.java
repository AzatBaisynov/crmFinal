package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Sales;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.service.SalesService;
import kg.itacademy.finalproject.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAll() {
        return salesService.getAll();
    }

    @GetMapping("/{id}")
    public Sales getById(@PathVariable Long id) {
        return salesService.getById(id);
    }

    @PostMapping
    public Sales create(@RequestBody Sales sales) {
        return salesService.create(sales);
    }

    @GetMapping("/list")
    public List<PurchaseSalesProductsListModel> getFullList(@RequestParam String date, Principal principal) {
        LocalDate localDate = LocalDate.parse(date);
        List<PurchaseSalesProductsListModel> fullList = salesService.getFullList(principal.getName(), localDate);
        if (fullList == null) return null;
        return fullList;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createByModel(@RequestBody PurchaseSalesCreateModel pSCM, Principal principal){
        String response = salesService.createByModel(principal.getName(), pSCM);
        if(response == "sold") return new ResponseEntity<>("Sold", HttpStatus.OK);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
