package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Purchase;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.model.StorageModel;
import kg.itacademy.finalproject.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping
    public List<Storage> getAll() {
        return storageService.getAll();
    }

    @GetMapping("/{id}")
    public Storage getById(@PathVariable Long id){
        return storageService.getById(id);
    }

    @PostMapping
    public Storage create(@RequestBody Storage storage) {
        return storageService.create(storage);
    }

    @GetMapping("/list")
    public List<StorageModel> getFullList(@RequestParam String date, Principal principal){
        LocalDate localDate = LocalDate.parse(date);
        return storageService.getFullList(principal.getName(), localDate);
    }
}
