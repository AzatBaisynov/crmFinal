package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.ProductsModel;
import kg.itacademy.finalproject.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByCompanyId(Long id) {
        return productRepository.getAllByCompanyId(id);
    }

    @Override
    public Product getByNameAndCompanyId(String name, Long id) {
        return productRepository.findByNameAndCompany_Id(name, id);
    }

    @Override
    public Product createByModel(String userName, ProductsModel productsModel) {
        Product product = new Product();
        User user = userService.getByLogin(userName);
        product.setCompany(user.getCompany());
        product.setName(productsModel.getProductName());
        return productRepository.save(product);
    }

}
