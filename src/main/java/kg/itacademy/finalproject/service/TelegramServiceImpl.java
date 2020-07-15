package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.*;
import kg.itacademy.finalproject.model.MainPageModel;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.model.StorageModel;
import kg.itacademy.finalproject.model.UsersProfitModel;
import kg.itacademy.finalproject.repo.TelegramRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TelegramServiceImpl implements TelegramService {

    @Autowired
    private MainPageService mainPageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private TelegramRegistrationRepo telegramRegistrationRepo;

    @Override
    public List<UsersProfitModel> getProfit(TelegramRegistration tg, LocalDate date) {
        Long companyId = tg.getId();
        List<User> user = userService.getAllByCompanyId(companyId);
        MainPageModel mainPage = mainPageService.getMainPage(user.get(0).getLogin(), date);
        return mainPage.getUsersProfitModels();
    }

    @Override
    public List<Product> getProducts(TelegramRegistration tg) {
        Long companyId = tg.getId();
        Company company = companyService.getById(companyId);
        return productService.getAllByCompanyId(companyId);
    }

    @Override
    public List<PurchaseSalesProductsListModel> getPurchases(TelegramRegistration tg, LocalDate date) {
        Long companyId = tg.getId();
        List<User> user = userService.getAllByCompanyId(companyId);
        return purchaseService.getFullList(user.get(0).getLogin(), date);
    }

    @Override
    public List<StorageModel> getStorages(TelegramRegistration tg, LocalDate date) {
        Long companyId = tg.getId();
        List<User> user = userService.getAllByCompanyId(companyId);
        return storageService.getFullList(user.get(0).getLogin());
    }

    @Override
    public List<PurchaseSalesProductsListModel> getSales(TelegramRegistration tg, LocalDate date) {
        Long companyId = tg.getId();
        List<User> user = userService.getAllByCompanyId(companyId);
        return salesService.getFullList(user.get(0).getLogin(), date);
    }

    @Override
    public String registerBot(String telegramKey, String telegramId) {
        System.out.println("ops");
        User user = userService.getByTelegramId(telegramKey);
        if (user != null) {
            telegramRegistrationRepo.save(new TelegramRegistration(telegramKey, telegramId, user.getCompany()));
            return "Регистрация завершена";
        }
        return "Введите ключ повторно";
    }

    @Override
    public TelegramRegistration companyId(String telegramId) {
        return telegramRegistrationRepo.findByTelegramId(telegramId);
    }
}