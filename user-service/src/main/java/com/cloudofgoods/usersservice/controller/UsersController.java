package com.cloudofgoods.usersservice.controller;


import com.cloudofgoods.usersservice.model.User;
import com.cloudofgoods.usersservice.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services")
//@EnableOAuth2Sso
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
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return customerService.save(users);
    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public User fetch(@PathVariable(value ="id") Long profileId) {
        return customerService.fetchById(profileId);
    }

//    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_admin')")
//    public  ResponseEntity<User[]>  fetch(Model model) {
//
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("Authorization", AccessToken.getAccessToken());
////        HttpEntity<User> customerHttpEntity = new HttpEntity<>(httpHeaders);
//
////            ResponseEntity<User[]> responseEntity =
////                    restTemplate.exchange("http://localhost:9191/services/profiles", HttpMethod.GET, customerHttpEntity, User[].class);
////            //model.addAttribute("profiles", responseEntity.getBody());
//
//
//        return responseEntity;
//    }
//        return customerService.fetchAllProfiles();
    }

