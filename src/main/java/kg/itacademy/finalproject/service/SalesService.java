package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Sales;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {
    Sales create(Sales sales);
    Sales getById(Long id);
    List<Sales> getAll();
    List<PurchaseSalesProductsListModel> getFullList(String userName, LocalDate date);
    String createByModel(String userName, PurchaseSalesCreateModel pSCM);
    List<Sales> getAllById(Long id);
}
