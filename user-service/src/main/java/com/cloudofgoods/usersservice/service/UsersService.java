package com.cloudofgoods.usersservice.service;

import com.cloudofgoods.usersservice.model.User;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface UsersService {


    User save(User customer);

    User fetchById(String profileId, String code, String contentType);

    List<User>  fetchAllProfiles(String code, String contentType) throws UnirestException;

    List<User> accountLockUser(String userName, String code, String type);
}
