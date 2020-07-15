package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.Sales;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MainPageServiceImpl implements MainPageService {
    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ImageService imageService;

    @Override
    public MainPageModel getMainPage(String userName, LocalDate date) {
        User user = userService.getByLogin(userName);
        Long id = user.getCompany().getId();
        List<Product> products = productService.getAllByCompanyId(id);
        List<User> users = userService.getAllByCompanyId(id);
        List<Storage> storages = storageService.getAllByCompanyId(id);
        List<PurchaseSalesProductsListModel> fullList = salesService.getFullList(userName, date);
        List<ProductsModel> list = getProducts(userName);

        List<Sales> salesServiceAll = salesService.getAllById(id);
        Set<String> names = new HashSet<>();
        salesServiceAll.forEach(x -> names.add(x.getUser().getUserFullName()));

        List<UsersProfitModel> list1 = new ArrayList<>();


        for (String name : names) {
            UsersProfitModel uspm = new UsersProfitModel();
            List<Earning> earnings = new ArrayList<>();
            Integer sum = 0;
            for (int i = 0; i < salesServiceAll.size(); i++){
                if(name.equals(salesServiceAll.get(i).getUser().getUserFullName())
                && date.toString().equals(salesServiceAll.get(i).getDateCreated().toLocalDate().toString())){
                    Earning earning = new Earning();
                    Storage byProduct = storageService.getByProduct(salesServiceAll.get(i).getProduct());
                    Integer priceStorage = byProduct.getPrice();
                    Integer priceSales = salesServiceAll.get(i).getPrice();
                    Integer middlePrice = priceSales - priceStorage;
                    Integer earningPrice = middlePrice * salesServiceAll.get(i).getCount();

                    String prodName = fullList.get(i).getProductName();
                    earning.setProductName(prodName);
                    earning.setEarning(earningPrice);
                    earning.setTime(salesServiceAll.get(i).getDateCreated());
                    earning.setImagePath(imageService.getImagePath(prodName, byProduct.getCompany().getId()));
                    earnings.add(earning);

                    sum = sum + earningPrice;
                }
            }
            uspm.setFullName(name);
            uspm.setEarnings(earnings);
            uspm.setTotalEarningsByDay(sum);
            list1.add(uspm);
        }
            MainPageModel mainPageModel = new MainPageModel(list1, list);
            return mainPageModel;
    }

    public List<ProductsModel> getProducts(String userName){
        User user = userService.getByLogin(userName);
        Long id = user.getCompany().getId();
        List<Product> products = productService.getAllByCompanyId(id);
        List<Storage> storages = storageService.getAllByCompanyId(id);
        List<Long> ids = new ArrayList<>();
        storages.forEach(x -> ids.add(x.getProduct().getId()));
        List<ProductsModel> list = new ArrayList<>();
        for(Storage x : storages){
            list.add(new ProductsModel(x.getProduct().getName(), x.getCount(), x.getCost(), imageService.getImagePath(x.getProduct().getName(),x.getCompany().getId())));
        }
        for(Product x : products){
            for(Long i : ids){
                if (x.getId() == i){
                    x.setName("none");
                }
            }
        }

        for(Product x : products){
            if (x.getName() != "none"){
                list.add(new ProductsModel(x.getName(), 0, 0,imageService.getImagePath(x.getName(),x.getCompany().getId())));
            }
        }
        return list;
    }




}
