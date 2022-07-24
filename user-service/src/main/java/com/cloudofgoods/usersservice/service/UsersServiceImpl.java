package com.cloudofgoods.usersservice.service;


import com.cloudofgoods.usersservice.model.User;
import com.cloudofgoods.usersservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public User save(User customer) {
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
