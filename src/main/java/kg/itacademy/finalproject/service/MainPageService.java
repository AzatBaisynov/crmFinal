package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.model.MainPageModel;

import java.time.LocalDate;

public interface MainPageService {
    MainPageModel getMainPage(String userName, LocalDate date);
}
