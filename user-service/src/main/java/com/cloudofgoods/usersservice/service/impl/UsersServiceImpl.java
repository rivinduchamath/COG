package com.cloudofgoods.usersservice.service.impl;


import com.cloudofgoods.usersservice.entity.User;
import com.cloudofgoods.usersservice.model.request.UserRegister;
import com.cloudofgoods.usersservice.repository.UsersRepository;
import com.cloudofgoods.usersservice.service.UsersService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    final RestTemplate restTemplate;

    final UsersRepository usersRepository;

    @Override
    public User save(User customer) {

        String getCustomerUri = "http://localhost:9191//registration/users";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();
        UserRegister userRegister = new UserRegister(
                customer.getUsername(),
                customer.getPassword(),
                customer.getPassword(),
                customer.getUsername());
        ParameterizedTypeReference<User> responseType = new ParameterizedTypeReference<User>() {};
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<Object>(userRegister,headers);

        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST,entity,responseType).getBody();
    }
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }
    @Override
    public User fetchById(String userName, String code, String contentType) {
        String getCustomerUri = "http://localhost:9191/getuser";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", code);
        headers.set("userName", userName);
        headers.set("Content-Type", contentType);
        ParameterizedTypeReference<User> responseType = new ParameterizedTypeReference<User>() {};

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);


        return restTemplate.exchange(uriComponents.toUri(),HttpMethod.GET,entity,responseType).getBody();
    }

    @Override
    public List<User> fetchAllProfiles(String code, String contentType) throws UnirestException {

        String getCustomerUri = "http://localhost:9191//greeting";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", code);
        headers.set("Content-Type", contentType);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>(){};

/*
        HttpResponse<JsonNode> ja = Unirest.get(builder.toUriString())
                .header("Authorization", code)
                .header("Content-Type", contentType)
                .asJson();
*/

        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, typeRef).getBody();
    }

    @Override
    public List<User> accountLockUser(String userName, String code, String type) {
        String getCustomerUri = "http://localhost:9191/accountLock/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", code);
        headers.set("Content-Type", type);
        headers.set("UserName", userName);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>(){};

        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, typeRef).getBody();
    }
}
