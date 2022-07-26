package com.cloudofgoods.auth.controller;

import com.cloudofgoods.auth.dto.CustomRolePermissionDTO;
import com.cloudofgoods.auth.dto.CustomUserRoleDTO;
import com.cloudofgoods.auth.entity.Role;
import com.cloudofgoods.auth.entity.User;
import com.cloudofgoods.auth.model.request.UserRegister;
import com.cloudofgoods.auth.service.RoleDetailsService;
import com.cloudofgoods.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private RoleDetailsService roleDetailsService;

    @RequestMapping(value = "/registration/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<OAuth2AccessToken> registerUser(Principal principal, @RequestBody @Valid UserRegister registrationRequest) {
    public User registerUser(Principal principal, @RequestBody @Valid UserRegister registrationRequest) {
        User user = userDetailService.registerUser(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail());
//        return tokenEndpoint.getAccessToken(principal, [grant_type: 'password', username: registrationRequest.email, password: registrationRequest.password])
        return user;
    }

    @RequestMapping(value = "/accountLock", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User lockUserAccount(HttpServletRequest request) {
        String userName = request.getHeader("UserName");
    return userDetailService.lockUserAccount(userName);
    }

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String assignRoleToUser() {
        return "ssssssss";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/removeRole")
    @ResponseStatus(HttpStatus.OK)
    public User removeRoleFromUser(@RequestBody CustomUserRoleDTO customUserRole) {
        return userDetailService.removeRoleFromUser(customUserRole.getUserName(),customUserRole.getRole());
    }
    @RequestMapping(method = RequestMethod.POST, value = "/assignPermission")
    @ResponseStatus(HttpStatus.OK)
    public Role assignPermissionToRole(@RequestBody CustomRolePermissionDTO customRolePermission) {
     return roleDetailsService.assignPermissionToRole(customRolePermission.getRoleName(),customRolePermission.getPermission());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/removePermission")
    @ResponseStatus(HttpStatus.OK)
    public Role removePermissionFromRole(@RequestBody CustomRolePermissionDTO customRolePermission) {
        return userDetailService.removePermissionFromRole(customRolePermission.getRoleName(),customRolePermission.getPermission());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    @ResponseStatus(HttpStatus.OK)
    public List<User> revokeToken() {
        return userDetailService.findAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getuser")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUser(HttpServletRequest request) {
        String userName = request.getHeader("userName");
        return userDetailService.getUserById(userName);
    }
}
