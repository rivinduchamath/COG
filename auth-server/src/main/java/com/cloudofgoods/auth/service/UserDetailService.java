package com.cloudofgoods.auth.service;

import com.cloudofgoods.auth.entity.Permission;
import com.cloudofgoods.auth.entity.Role;
import com.cloudofgoods.auth.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {

    User registerUser(String username, String password, String email);

    List<User> findAllUsers();

    Optional<User> getUserById(String userName);

    Role removePermissionFromRole(String role, Permission permission);

    User removeRoleFromUser(String userName, Role role);

    User lockUserAccount(String userName);
}
