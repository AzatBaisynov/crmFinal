package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.model.StorageModel;

import java.util.List;

public interface StorageService {
    Storage create(Storage storage);
    Storage getById(Long id);
    List<Storage> getAll();
    List<StorageModel> getFullList(String userName);
    Storage getByProduct(Product product);
    List<Storage> getAllByCompanyId(Long id);
    void delete(Storage storage);
}
