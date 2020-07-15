package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Sales;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.model.PurchaseReturn;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.model.SalesReturn;
import kg.itacademy.finalproject.service.SalesService;
import kg.itacademy.finalproject.service.StorageService;
import kg.itacademy.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

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
    public SalesReturn getFullList(@RequestParam String date, Principal principal) {
        LocalDate localDate = LocalDate.parse(date);
        List<PurchaseSalesProductsListModel> fullList = salesService.getFullList(principal.getName(), localDate);
        List<Storage> storages = storageService.getAllByCompanyId(userService.getByLogin(principal.getName()).getCompany().getId());
        List<String> products = new ArrayList<>();
        storages.forEach(x->products.add(x.getProduct().getName()));

        SalesReturn pR = new SalesReturn(fullList, products);
        return pR;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createByModel(@RequestBody PurchaseSalesCreateModel pSCM, Principal principal){
        String response = salesService.createByModel(principal.getName(), pSCM);
        if(response == "sold") return new ResponseEntity<>("Sold", HttpStatus.OK);
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
