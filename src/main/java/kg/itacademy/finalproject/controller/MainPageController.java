package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.model.MainPageModel;
import kg.itacademy.finalproject.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainPageController {
    @Autowired
    private MainPageService mainPageService;

    @GetMapping
    public MainPageModel getMainPage(@RequestParam String date, Principal principal){
        LocalDate localDate = LocalDate.parse(date);
        return mainPageService.getMainPage(principal.getName(), localDate);
    }
}
