package com.cloudofgoods.usersservice.controller;


import com.cloudofgoods.usersservice.model.Users;
import com.cloudofgoods.usersservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class UsersController {


    @Autowired
    UsersService customerService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('create_profile')")
    public Users save(@RequestBody Users users) {
        return customerService.save(users);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public Users fetch(@RequestParam int profileId) {
        return customerService.fetchById(profileId);
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_operator')")
    public List<Users> fetch() {
        return customerService.fetchAllProfiles();
    }
}
