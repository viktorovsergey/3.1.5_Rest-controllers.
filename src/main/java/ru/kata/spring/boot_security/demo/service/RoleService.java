package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRole();
    List<Role> getRolesById(List<Long> roleIds);
    Optional<Role> findRoleByName(String roleName);
}
