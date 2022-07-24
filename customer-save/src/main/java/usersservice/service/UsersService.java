package usersservice.service;


import usersservice.model.User;

import java.util.List;

public interface UsersService {


    User save(User customer);

    User fetchById(Long profileId);

    List<User> fetchAllProfiles();
}
