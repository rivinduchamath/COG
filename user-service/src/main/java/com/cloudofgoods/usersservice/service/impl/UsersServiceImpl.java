package com.cloudofgoods.usersservice.service.impl;


import com.cloudofgoods.usersservice.entity.User;
import com.cloudofgoods.usersservice.model.request.UserRegister;
import com.cloudofgoods.usersservice.repository.UsersRepository;
import com.cloudofgoods.usersservice.service.UsersService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
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
    public User fetchById(Long profileId) {
        String getCustomerUri = "http://localhost:9191/getUser";
        log.info("Calling Customer service using the uri: " + getCustomerUri);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();

        ParameterizedTypeReference<User> responseType = new ParameterizedTypeReference<User>() {};
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

            User body = restTemplate.exchange(uriComponents.toUri(),HttpMethod.GET,entity,responseType).getBody();
           // return CompletableFuture.completedFuture(body).exceptionally(e -> new User());
        System.out.println("body  "+ body);
        return body;
//
//        Optional<User> customer = usersRepository.findById(profileId);
//        return customer.orElse(null);
    }

    @Override
    public List<User> fetchAllProfiles(String code, String contentType) throws UnirestException {
        String getCustomerUri = "http://localhost:9191//greeting";


        ParameterizedTypeReference<User> responseType = new ParameterizedTypeReference<User>() {};
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getCustomerUri); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", code);
        headers.set("Content-Type", contentType);

//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);


        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>(){};
        List<User> users = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, typeRef).getBody();

/*        HttpResponse<JsonNode> ja = Unirest.get(builder.toUriString())
                .header("Authorization", code)
                .header("Content-Type", contentType)
                .asJson();*/


//        Mono<List<User>> response = WebClient.create()
//                .get()
//                .uri("http://localhost:9191//greeting") .header("Authorization", code)
//                .header("Content-Type", contentType)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

//        List<User> readers = response.block();
      //  System.out.println(response.block());
//        return readers.stream()
//                .map(Reader::getFavouriteBook)
//                .collect(Collectors.toList());

//        tweetFlux.subscribe(tweet -> log.info(tweet.toString()));



        return users;
    }
}
