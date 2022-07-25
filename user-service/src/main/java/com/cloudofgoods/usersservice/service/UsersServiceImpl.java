package com.cloudofgoods.usersservice.service;


import com.cloudofgoods.usersservice.model.User;
import com.cloudofgoods.usersservice.model.request.UserRegister;
import com.cloudofgoods.usersservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public User save(User customer) {
        String getCustomerUri = "http://localhost:9191//registration/users";
//        log.info("Calling Customer service using the uri: " + getCustomerUri);
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//        body.add("username",customer.getUsername());
//        body.add("password",customer.getPassword());
//        body.add("matchingPassword",customer.getPassword());
//        body.add("email",customer.getUsername());


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();
        UserRegister userRegister = new UserRegister(customer.getUsername(), customer.getPassword(), customer.getPassword(), customer.getUsername());
        ParameterizedTypeReference<User> responseType = new ParameterizedTypeReference<User>() {};
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<Object>(userRegister,headers);
//        try {
        User respose = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST,entity,responseType).getBody();
//            return CompletableFuture.completedFuture(respose).exceptionally(e -> new UserRegister());
//        } catch (HttpClientErrorException e) {
//            return CompletableFuture.completedFuture(new UserRegister());
//        }
        System.out.println(respose.toString());
        return usersRepository.save(customer);
    }

    @Override
    public User fetchById(Long profileId) {
        Optional<User> customer = usersRepository.findById(profileId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    @Override
    public List<User> fetchAllProfiles() {
        return usersRepository.findAll();
    }
}
