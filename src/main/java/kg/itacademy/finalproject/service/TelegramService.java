package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.*;
import kg.itacademy.finalproject.model.PurchaseSalesProductsListModel;
import kg.itacademy.finalproject.model.StorageModel;
import kg.itacademy.finalproject.model.UsersProfitModel;

import java.time.LocalDate;
import java.util.List;

public interface TelegramService {
    List<UsersProfitModel> getProfit(TelegramRegistration tg, LocalDate date);
    List<Product> getProducts(TelegramRegistration tg);
    List<PurchaseSalesProductsListModel> getPurchases(TelegramRegistration tg, LocalDate date);
    List<StorageModel> getStorages(TelegramRegistration tg, LocalDate date);
    List<PurchaseSalesProductsListModel> getSales(TelegramRegistration tg, LocalDate date);
    String registerBot(String telegramKey, String telegramId);
    TelegramRegistration companyId(String telegramId);

}
