package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.StorageModel;
import kg.itacademy.finalproject.repo.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Override
    public Storage create(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Storage getById(Long id) {
        return storageRepository.getOne(id);
    }

    @Override
    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    @Override
    public List<StorageModel> getFullList(String userName) {
        User user = userService.getByLogin(userName);
        Long companyId = user.getCompany().getId();
        List<Storage> storages = storageRepository.findAllByCompanyIdAndCountGreaterThan(companyId, 0);
        List<StorageModel> storagesForReturn = new ArrayList<>();

        for (int i = 0; i < storages.size(); i++){
                storagesForReturn.add(new StorageModel(
                        storages.get(i).getProduct().getName(),
                        storages.get(i).getCount(),
                        storages.get(i).getCost() / storages.get(i).getCount(),
                        storages.get(i).getCost(),
                        imageService.getImagePath(storages.get(i).getProduct().getName(), storages.get(i).getCompany().getId())
                ));
        }
        return storagesForReturn;
    }

    @Override
    public Storage getByProduct(Product product) {
        return storageRepository.findByProduct(product);
    }

    @Override
    public List<Storage> getAllByCompanyId(Long id) {
        return storageRepository.findAllByCompanyIdAndCountGreaterThan(id, 0);
    }

    @Override
    public void delete(Storage storage) {
        storageRepository.delete(storage);
    }

}
