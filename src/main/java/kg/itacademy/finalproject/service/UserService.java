package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.model.LoginModel;
import kg.itacademy.finalproject.model.RegistrationModel;

import java.util.List;

public interface UserService {
    User create(User user);
    User getById(Long id);
    List<User> getAll();
    String getToken(LoginModel loginModel);
    User getByLogin(String login);
    User createUserAndUserRole(RegistrationModel registrationModel, Long id);
    List<User> getAllByCompanyId(Long id);
    User getByTelegramId(String id);
    User createUserByAdmin(String name, RegistrationModel registrationModel);
}
