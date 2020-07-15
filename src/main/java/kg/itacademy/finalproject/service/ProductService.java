package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.model.ProductsModel;


import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
    List<Product> getAll();
    List<Product> getAllByCompanyId(Long id);
    Product getByNameAndCompanyId(String name, Long id);
    Product createByModel(String userName, ProductsModel productsModel);
    List<String> getProductNames(String userName);
}
