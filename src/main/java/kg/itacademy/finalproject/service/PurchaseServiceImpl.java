package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.*;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;

    @Override
    public Purchase create(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getById(Long id) {
        return purchaseRepository.getOne(id);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<PurchaseSalesProductsListModel> getFullList(String userName, LocalDate date) {
        User user = userService.getByLogin(userName);
        Long companyId = user.getCompany().getId();
        List<Purchase> purchases = purchaseRepository.findAllByCompanyId(companyId);
        List<PurchaseSalesProductsListModel> purchasesForReturn = new ArrayList<>();

        for(int i = 0; i < purchases.size(); i++){
            if(purchases.get(i).getDateCreated().toLocalDate().toString().equals(date.toString())) {
                purchasesForReturn.add(new PurchaseSalesProductsListModel(
                        purchases.get(i).getProduct().getName(),
                        purchases.get(i).getCount(),
                        purchases.get(i).getPrice(),
                        purchases.get(i).getUser().getUserFullName(),
                        purchases.get(i).getPrice() *purchases.get(i).getCount(),
                        date
                        ));
            }
        }
        return purchasesForReturn;
    }

    @Override
    public String createByModel(String userName, PurchaseSalesCreateModel pSCM) {
        User user = userService.getByLogin(userName);
        Company company = user.getCompany();
        Purchase purchase = new Purchase();
        Product product = productService.getByNameAndCompanyId(pSCM.getProduct(), company.getId());
        Storage storage = storageService.getByProduct(product);
        Integer count = pSCM.getCount();
        Integer price = count * pSCM.getPricePerUnit();
        if (storage == null) {
            Storage storage1 = new Storage();
            storage1.setProduct(product);
            storage1.setCount(count);
            storage1.setPrice(pSCM.getPricePerUnit());
            storage1.setCompany(company);
            storage1.setCost(price);
            storageService.create(storage1);
        } else {
            Integer sCount = storage.getCount();
            Integer sCost = storage.getCost();
            Integer pCost = pSCM.getCount() * pSCM.getPricePerUnit();
            Integer newSCount = sCount + pSCM.getCount();
            Integer newSCost = sCost + pCost;

            storage.setCount(newSCount);
            storage.setCost(newSCost);
            storage.setPrice(newSCost / newSCount);
            storageService.create(storage);
        }
        purchase.setProduct(product);
        purchase.setCount(pSCM.getCount());
        purchase.setPrice(pSCM.getPricePerUnit());
        purchase.setCompany(company);
        purchase.setUser(user);
        if (create(purchase) != null)
            return "Created";
        return null;
    }
}
