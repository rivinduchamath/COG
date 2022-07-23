package com.cloudofgoods.usersservice.service;


import com.cloudofgoods.usersservice.model.Users;
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
    public Users save(Users customer) {
        return usersRepository.save(customer);
    }

    @Override
    public Users fetchById(int profileId) {
        Optional<Users> customer = usersRepository.findById(profileId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Users> fetchAllProfiles() {
        return usersRepository.findAll();
    }
}
