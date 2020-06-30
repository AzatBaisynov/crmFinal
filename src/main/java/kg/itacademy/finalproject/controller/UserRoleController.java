package kg.itacademy.finalproject.controller;

import kg.itacademy.finalproject.entity.UserRole;
import kg.itacademy.finalproject.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<UserRole> getAll() {
        return userRoleService.getAll();
    }

    @GetMapping("/{id}")
    public UserRole getById(@PathVariable Long id){
        return userRoleService.getById(id);
    }

    @PostMapping
    public UserRole create(@RequestBody UserRole userRole) {
        return userRoleService.create(userRole);
    }
}
