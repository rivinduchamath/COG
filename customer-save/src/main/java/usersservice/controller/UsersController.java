package usersservice.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import usersservice.model.User;
import usersservice.service.UsersService;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class UsersController {


    @Autowired
    UsersService customerService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public User save(@RequestBody User users) {
        return customerService.save(users);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyAuthority('') and hasAnyRole('')")
    public User saveCustomer(@RequestBody User users) {
        return customerService.save(users);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public User fetch(@RequestParam Long profileId) {
        return customerService.fetchById(profileId);
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_admin')")
    public List<User> fetch() {
        return customerService.fetchAllProfiles();
    }
}
