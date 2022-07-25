package com.cloudofgoods.auth.service.impl;

import com.cloudofgoods.auth.dao.UserDetailRepository;
import com.cloudofgoods.auth.entity.AuthUserDetail;
import com.cloudofgoods.auth.entity.User;
import com.cloudofgoods.auth.repository.RoleDetailsRepository;
import com.cloudofgoods.auth.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService , UserDetailService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserDetailRepository userDetailRepository;

    @Autowired
    private RoleDetailsRepository roleDetailRepository;

    @Override
    public User registerUser(String username,String password,String email){
        User user = new User();
        user.setUsername(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

//        Optional<Role> byId = roleDetailRepository.findById(Role.ROLE_user);
//        if(byId.isPresent()){
//            Role role1 = byId.get();
//            user.setRoles(role1);
//        }
        return userDetailRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDetailRepository.findAll();
    }

    @Override
    // Load User From the database and hand over to the UserDetails service
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;


    }
}
