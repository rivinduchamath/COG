package com.cloudofgoods.auth.service.impl;

import com.cloudofgoods.auth.entity.Permission;
import com.cloudofgoods.auth.entity.Role;
import com.cloudofgoods.auth.repository.RoleDetailsRepository;
import com.cloudofgoods.auth.service.RoleDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleDetailsServiceImpl implements RoleDetailsService {
    @Autowired
    private RoleDetailsRepository roleDetailRepository;
    @Override
    public Role assignPermissionToRole(String roleName, Permission permission) {
        Optional<Role> optionalRole = roleDetailRepository.findRoleByName(roleName);
        // Get All Permissions
        Role roleDetails = new Role(optionalRole.get());

        List<Permission> currentPermission = roleDetails.getPermissions();

        for (Permission p : currentPermission) {
            if (!p.equals(permission)) {
                if (!currentPermission.contains(p)) currentPermission.add(p);
            }
        }

        roleDetails.setPermissions(currentPermission);
        return roleDetailRepository.save(roleDetails);
    }

    @Override
    public Role createRole(String roleName) {

        return roleDetailRepository.save(new Role(null,roleName));
    }

    @Override
    @Transactional
    public int removeRole(String roleName) {
        return  roleDetailRepository.deleteRoleByName(roleName);

    }
}
