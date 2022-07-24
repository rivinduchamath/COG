package usersservice.service;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersservice.model.User;
import usersservice.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {


 final UsersRepository usersRepository;

    @Override
    public User save(User customer) {
        return usersRepository.save(customer);
    }

    @Override
    public User fetchById(Long profileId) {
        Optional<User> customer = usersRepository.findById(profileId);
        return customer.orElse(null);
    }

    @Override
    public List<User> fetchAllProfiles() {
        return usersRepository.findAll();
    }
}
