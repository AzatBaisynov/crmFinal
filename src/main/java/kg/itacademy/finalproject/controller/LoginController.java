package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.Company;
import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.LoginModel;
import kg.itacademy.finalproject.model.RegistrationModel;
import kg.itacademy.finalproject.service.CompanyService;
import kg.itacademy.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<String> getTelegramKey(Principal principal){
        String key = userService.getTelegramKey(principal.getName());
        if (key == null) return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }


    @PostMapping("/auth")
    public ResponseEntity<String> getToken(@RequestBody LoginModel loginModel) {
        String result = userService.getToken(loginModel);
        if (result.equals("Error")) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody RegistrationModel registrationModel) {
        Company company = new Company();
        company.setCompanyName(registrationModel.getCompanyName());
        company.setCash(0);
        Company company1 = companyService.create(company);
        Long id = company1.getId();
        User user = userService.createUserAndUserRole(registrationModel, id);
        LoginModel lm = new LoginModel();
        lm.setLogin(registrationModel.getLogin());
        lm.setPassword(registrationModel.getPassword());
        String result = userService.getToken(lm);
        if (user == null){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registration/user")
    public ResponseEntity<String> createNewUser(@RequestBody RegistrationModel registrationModel, Principal principal) {
        String login = principal.getName();
        User user = userService.createUserByAdmin(login, registrationModel);
        if (user == null){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User created! Your telegram key is: " + user.getTelegramKey(), HttpStatus.OK);
    }
}
