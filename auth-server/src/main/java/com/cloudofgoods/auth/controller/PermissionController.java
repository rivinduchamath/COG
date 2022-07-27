package com.cloudofgoods.auth.controller;

import com.cloudofgoods.auth.service.PermissionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PermissionController {
    final PermissionDetailsService permissionDetailsService;
    @RequestMapping(value = "/permission/create/{permissionName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String createPermission(@PathVariable(name = "permissionName") String permissionName){
        return permissionDetailsService.savePermission(permissionName);
    }
    @RequestMapping(value = "/permission/delete/{permissionId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean removePermission(@PathVariable(name = "permissionId") Integer permissionId){
        return permissionDetailsService.delete(permissionId);
    }

    public void updatePermission(){}

}
