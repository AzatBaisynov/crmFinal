package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.MainPageModel;
import kg.itacademy.finalproject.model.ProductsModel;

import java.time.LocalDate;
import java.util.List;

public interface MainPageService {
    MainPageModel getMainPage(String userName, LocalDate date);
    List<ProductsModel> getProducts(String userName);
}
