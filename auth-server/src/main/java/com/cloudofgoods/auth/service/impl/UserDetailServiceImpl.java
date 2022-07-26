package com.cloudofgoods.auth.service.impl;

import com.cloudofgoods.auth.entity.*;
import com.cloudofgoods.auth.dao.PermissionDetailsRepository;
import com.cloudofgoods.auth.dao.RoleDetailsRepository;
import com.cloudofgoods.auth.dao.UserDetailRepository;
import com.cloudofgoods.auth.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService, UserDetailService {
    private final UserDetailRepository userDetailRepository;
    private final PermissionDetailsRepository permissionDetailsRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleDetailsRepository roleDetailRepository;

    @Override
    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        return userDetailRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDetailRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userName) {
        return userDetailRepository.findByUsername(userName);
    }

    @Override
    public Role removePermissionFromRole(String roleName, Permission  permission) {
        // Find Role If Exists
        Optional<Role> optionalRole = roleDetailRepository.findRoleByName(roleName);
        // Get All Permissions

        Role roledetails = new Role(optionalRole.get());

        List<Permission> list = new ArrayList<>();
        for (Permission p : roledetails.getPermissions()) {
                if (!p.equals(permission)) {
                    if (!list.contains(p)) list.add(p);
                }
        }
        roledetails.setPermissions(list);
        return roleDetailRepository.save(roledetails);
    }

    @Override
    public User removeRoleFromUser(String userName, Role role) {
        // Find user If Exists
        Optional<User> user =  userDetailRepository.findByUsername(userName);
        User userDetails = new User(user.get());
        List<Role> list = new ArrayList<>();
        for (Role p : userDetails.getRoles()) {
            System.out.println("role "+ role);

            if (!p.getName().equals(role.getName())) {
                if (!list.contains(p)) list.add(p);
            }
        }
        userDetails.setRoles(null);
        userDetails.setRoles(list);

        return userDetailRepository.save(userDetails);
    }

    @Override
    public User lockUserAccount(String userName) {
        Optional<User> user =  userDetailRepository.findByUsername(userName);
        User userDetails = new User(user.get());
        userDetails.setAccountNonLocked(false);
        userDetailRepository.save(userDetails);
        return userDetails;
    }

    @Override
    // Load User From the database and hand over to the UserDetails service
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(userName);
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
        //  return loadUser( name);
    }

    private CustomUserDetails loadUser(String name) throws UsernameNotFoundException {

//        XYZ xname = xyzrepo.getRoleByUserName(name);
//        if (xname==null){
//            return null;
//        }
//        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//
//        for (int i=0; i<xname.getRoles().size();i++)
//        {
//            JSONObject jsonObject = new JSONObject(xname.getRoles().get(i));
//            String role = jsonObject.getString("role");
//            gas.add(new SimpleGrantedAuthority(role));
//        }
//        return new CustomUserDetails(xname.getEmail(),xname.getDisplayName(),xname.getUserName(),xname.getPassword(),xname.getEnabled(),gas);
        return null;
    }

    public UserDetails loadUserByUsernameWithoutCredentials(String s) throws UsernameNotFoundException, JSONException {
        CustomUserDetails customUserDetails = loadUser(s);
        if (customUserDetails != null) {
            customUserDetails.eraseCredentials();
        }
        return customUserDetails;
    }
}
