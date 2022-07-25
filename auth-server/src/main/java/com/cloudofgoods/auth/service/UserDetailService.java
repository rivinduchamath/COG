package com.cloudofgoods.auth.service;

import com.cloudofgoods.auth.entity.User;

public interface UserDetailService {

    User registerUser(String username, String password, String email);
}
