package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Company;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
