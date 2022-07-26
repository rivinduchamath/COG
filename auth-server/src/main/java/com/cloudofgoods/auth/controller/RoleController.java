package com.cloudofgoods.auth.controller;

import com.cloudofgoods.auth.entity.Role;
import com.cloudofgoods.auth.service.RoleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    RoleDetailsService roleDetailsService;

    @RequestMapping(method = RequestMethod.POST, value = "/createRole/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public Role createRole(@PathVariable(name ="roleName") String roleName){
      return roleDetailsService.createRole( roleName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/removeRole/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public int removeRole(@PathVariable(name ="roleName") String roleName){
        return roleDetailsService.removeRole( roleName);
    }

    public void updateRole(){

    }
}
