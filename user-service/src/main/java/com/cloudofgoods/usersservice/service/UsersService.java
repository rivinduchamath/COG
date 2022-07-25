package com.cloudofgoods.usersservice.service;

import com.cloudofgoods.usersservice.entity.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface UsersService {


    User save(User customer);

    User fetchById(Long profileId);

    List<User>  fetchAllProfiles(String code, String contentType) throws UnirestException;
}
