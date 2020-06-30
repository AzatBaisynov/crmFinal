package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Purchase;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    Purchase create(Purchase purchase);
    Purchase getById(Long id);
    List<Purchase> getAll();
    List<PurchaseSalesProductsListModel> getFullList(String userName, LocalDate date);
    String createByModel(String userName, PurchaseSalesCreateModel pSCM);
}
