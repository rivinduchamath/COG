package com.cloudofgoods.usersservice.controller;


import com.cloudofgoods.usersservice.entity.User;
import com.cloudofgoods.usersservice.service.UsersService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services")
public class UsersController {

    final RestTemplate restTemplate;
    final UsersService customerService;

    @RequestMapping(value = "/s",method = RequestMethod.GET)
    public String loadUI() {
        return "home";
    }

    @RequestMapping(value = "/secure",method = RequestMethod.GET)
    public String loadSecuredUI() {
        return "secure";
    }

    //Save User With Credentials
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('create_profile')")
    public User save(@RequestBody User users) {
        return customerService.save(users);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public User saveCustomer(@RequestBody User users) {
        return customerService.save(users);
    }


    @RequestMapping(value = "/getprofile/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public User fetch(@PathVariable(name ="username" ,required = false) String username, HttpServletRequest request,Principal principal) {
//        String loggedUser = principal.getName();
        String code = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-Type");
        return customerService.fetchById(username,code,contentType);
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_admin')")
    public List<User> userDetails(HttpServletRequest request) throws UnirestException {

        String code = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-Type");
        return customerService.fetchAllProfiles(code,contentType);
    }

    @RequestMapping(value = "/accountLockUser", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_admin')")
    public List<User> accountLockUser(HttpServletRequest request) throws UnirestException {

        String code = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-Type");
        String userName = request.getHeader("UserName");
        return customerService.accountLockUser(userName,code,contentType);
    }


}

