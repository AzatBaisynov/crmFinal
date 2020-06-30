package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.entity.UserRole;
import kg.itacademy.finalproject.model.LoginModel;
import kg.itacademy.finalproject.model.RegistrationModel;
import kg.itacademy.finalproject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String getToken(LoginModel loginModel) {
        User user = getByLogin(loginModel.getLogin());
        if(user == null) return "Error";
        String rawPassword = loginModel.getPassword();
        String encodedPassword = user.getPassword();
        if(passwordEncoder.matches(rawPassword, encodedPassword)){
            String loginPasswordPair = user.getLogin() + ":" + loginModel.getPassword();
            String token = Base64.getEncoder().encodeToString(loginPasswordPair.getBytes());
            return "Basic " + token;
        }
        return "Error";
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public User createUserAndUserRole(RegistrationModel registrationModel, Long id) {
        Random rd = new Random();
        User user = new User();
        String encodedPassword = passwordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encodedPassword);
        user.setLogin(registrationModel.getLogin());
        user.setUserFullName(registrationModel.getFullName());
        user.setAge(registrationModel.getAge());
        user.setPosition(registrationModel.getPosition());
        user.setCompany(companyService.getById(id));
        user.setTelephone(registrationModel.getTelephone());
        user.setStatus(1);
        user.setTelegramKey("Key " + user.getLogin() + System.currentTimeMillis() + rd.nextInt(1000));
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        userRole.setUser(user);
        userRoleService.create(userRole);
        return user;
    }

    @Override
    public User createUserByAdmin(String name, RegistrationModel registrationModel) {
        User user = new User();
        User admin = userRepository.getByLogin(name);
        String encodedPassword = passwordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encodedPassword);
        user.setLogin(registrationModel.getLogin());
        user.setUserFullName(registrationModel.getFullName());
        user.setAge(registrationModel.getAge());
        user.setPosition(registrationModel.getPosition());
        user.setCompany(admin.getCompany());
        user.setTelephone(registrationModel.getTelephone());
        user.setStatus(1);
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUser(user);
        userRoleService.create(userRole);
        return user;
    }

    @Override
    public List<User> getAllByCompanyId(Long id) {
        return userRepository.findAllByCompanyId(id);
    }

    @Override
    public User getByTelegramId(String id) {
        return userRepository.findByTelegramKey(id);
    }


}
