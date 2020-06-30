package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.Product;
import kg.itacademy.finalproject.entity.Sales;
import kg.itacademy.finalproject.entity.Storage;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.Earning;
import kg.itacademy.finalproject.model.MainPageModel;
import kg.itacademy.finalproject.model.ProductsModel;
import kg.itacademy.finalproject.model.UsersProfitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;
import sun.plugin.util.UserProfile;

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

    @Override
    public MainPageModel getMainPage(String userName, LocalDate date) {
        User user = userService.getByLogin(userName);
        Long id = user.getCompany().getId();
        List<Product> products = productService.getAllByCompanyId(id);
        List<User> users = userService.getAllByCompanyId(id);
        List<Storage> storages = storageService.getAllByCompanyId(id);
        List<ProductsModel> listBefore = new ArrayList<>();
        for (int i = 0; i < storages.size(); i++) {
            String name = storages.get(i).getProduct().getName();
            Integer count = storages.get(i).getCount();
            Integer totalPrice = storages.get(i).getCost();
            listBefore.add(new ProductsModel(name, count, totalPrice));
        }
        for (int i = 0; i < listBefore.size(); i++) {
            boolean isContains = false;
            int k = 0;
            for (int j = 0; j < products.size(); j++) {
                if (!listBefore.get(i).getProductName().equals(products.get(j).getName())) {
                    isContains = false;
                    k++;
                }
                else {
                    isContains = true;
                }
            }
            if (isContains == false) {
                listBefore.add(new ProductsModel(products.get(k).getName(), 0, 0));
            }
            k = 0;
            isContains = false;
        }
        Set<ProductsModel> listMiddle = new HashSet<>();
        listBefore.forEach(x -> listMiddle.add(x));
        List<ProductsModel> list = new ArrayList<>();
        listMiddle.forEach(x -> list.add(x));

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

                    earning.setProductName(salesServiceAll.get(i).getProduct().getName());
                    earning.setEarning(earningPrice);
                    earning.setTime(salesServiceAll.get(i).getDateCreated());
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




}
