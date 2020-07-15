package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.User;
import kg.itacademy.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/telegram")
    public Map<String, String> getTelegramKey(Principal principal) {
        User user = userService.getByLogin(principal.getName());
        String tKey = user.getTelegramKey();
        Map<String, String> map = new HashMap<>();
        map.put("telegramKey", tKey);
        return map;
    }
}
