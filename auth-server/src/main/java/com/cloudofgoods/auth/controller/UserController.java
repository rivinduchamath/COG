package com.cloudofgoods.auth.controller;


import com.cloudofgoods.auth.entity.User;
import com.cloudofgoods.auth.model.request.UserRegister;
import com.cloudofgoods.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping(value = "/registration/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<OAuth2AccessToken> registerUser(Principal principal, @RequestBody @Valid UserRegister registrationRequest) {
    public User registerUser(Principal principal, @RequestBody @Valid UserRegister registrationRequest) {
        User user = userDetailService.registerUser(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail());
//        return tokenEndpoint.getAccessToken(principal, [grant_type: 'password', username: registrationRequest.email, password: registrationRequest.password])
            return user;
    }

    public void lockUserAccount(boolean lock){

    }

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)

    public String assignRoleToUser(){
        return "ssssssss";
    }

    public void removeRoleFromUser(){

    }

    public void assignPermissionToUser(){

    }

    public void removePermissionFromUser(){

    }

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    @ResponseStatus(HttpStatus.OK)
    public String revokeToken(HttpServletRequest request) {
       return "done";
    }

}
