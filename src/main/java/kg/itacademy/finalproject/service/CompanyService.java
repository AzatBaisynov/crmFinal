package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Company;

import java.util.List;

public interface CompanyService {
    Company create(Company company);
    Company getById(Long id);
    List<Company> getAll();
}
