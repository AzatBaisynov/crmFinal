package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.*;
import kg.itacademy.finalproject.model.PurchaseSalesCreateModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.repo.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ImageService imageService;

    @Override
    public Sales create(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public Sales getById(Long id) {
        return salesRepository.getOne(id);
    }

    @Override
    public List<Sales> getAll() {
        return salesRepository.findAll();
    }

    @Override
    public List<PurchaseSalesProductsListModel> getFullList(String userName, LocalDate date) {
        User user = userService.getByLogin(userName);
        Long companyId = user.getCompany().getId();
        List<Sales> sales = salesRepository.findAllByCompanyId(companyId);
        List<PurchaseSalesProductsListModel> salesForReturn = new ArrayList<>();



        for(int i = 0; i < sales.size(); i++){
            if(sales.get(i).getDateCreated().toLocalDate().toString().equals(date.toString())){
                salesForReturn.add(new PurchaseSalesProductsListModel(
                        sales.get(i).getProduct().getName(),
                        sales.get(i).getCount(),
                        sales.get(i).getPrice(),
                        sales.get(i).getUser().getUserFullName(),
                        sales.get(i).getPrice() * sales.get(i).getCount(),
                        date,
                        imageService.getImagePath(sales.get(i).getProduct().getName(), sales.get(i).getCompany().getId())));
            }
        }
        return salesForReturn;
    }

    @Override
    public String createByModel(String userName, PurchaseSalesCreateModel pSCM) {
        User user = userService.getByLogin(userName);
        Company company = user.getCompany();
        Sales sales = new Sales();
        Product product = productService.getByNameAndCompanyId(pSCM.getProduct(), company.getId());
        Storage storage = storageService.getByProduct(product);
        if(pSCM.getCount() > storage.getCount()) return "error, you dont have so much Product";
        Integer count = pSCM.getCount();
        Integer price = pSCM.getPricePerUnit() * count;
        sales.setProduct(product);
        sales.setCount(count);
        sales.setPrice(pSCM.getPricePerUnit());
        sales.setCompany(company);
        sales.setUser(user);
        Integer count1 = storage.getCount();
        Integer unitPriceStorage = storage.getPrice();
        Integer costStorage = storage.getCost();
        Integer subtraction = pSCM.getCount() * unitPriceStorage;
        storage.setCount(count1-count);
        storage.setCost(costStorage - subtraction);
        storageService.create(storage);
        Sales save = salesRepository.save(sales);
        if (save == null) return null;
        return "sold";
    }

    @Override
    public List<Sales> getAllById(Long id) {
        return salesRepository.findAllByCompanyId(id);
    }
}
