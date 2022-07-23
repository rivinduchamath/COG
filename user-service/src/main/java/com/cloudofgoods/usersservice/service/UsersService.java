package com.cloudofgoods.usersservice.service;

import com.cloudofgoods.usersservice.model.Users;


import java.util.List;

public interface UsersService {


    Users save(Users customer);

    Users fetchById(int profileId);

    List<Users> fetchAllProfiles();
}
