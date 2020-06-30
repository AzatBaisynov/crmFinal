package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Company;
import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }
}
