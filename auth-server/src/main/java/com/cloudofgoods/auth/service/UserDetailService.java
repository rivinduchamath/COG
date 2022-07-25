package com.cloudofgoods.auth.service;

import com.cloudofgoods.auth.entity.User;

import java.util.List;

public interface UserDetailService {

    User registerUser(String username, String password, String email);

    List<User> findAllUsers();
}
