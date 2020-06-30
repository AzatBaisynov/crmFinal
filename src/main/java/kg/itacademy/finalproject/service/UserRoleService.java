package kg.itacademy.finalproject.service;

import kg.itacademy.finalproject.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole create(UserRole userRole);
    UserRole getById(Long id);
    List<UserRole> getAll();
}
