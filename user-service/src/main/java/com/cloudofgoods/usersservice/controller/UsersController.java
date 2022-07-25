package com.cloudofgoods.usersservice.controller;


import com.cloudofgoods.usersservice.entity.User;
import com.cloudofgoods.usersservice.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services")
public class UsersController {

    final RestTemplate restTemplate;
    final UsersService customerService;

    @RequestMapping(value = "/")
    public String loadUI() {
        return "home";
    }

    @RequestMapping(value = "/secure")
    public String loadSecuredUI() {
        return "secure";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('create_profile')")
    public User save(@RequestBody User users) {
        return customerService.save(users);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public User saveCustomer(@RequestBody User users) {
        return customerService.save(users);
    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public User fetch(@PathVariable(value ="id") Long profileId) {
        return customerService.fetchById(profileId);
    }

    }

