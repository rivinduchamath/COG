package com.cloudofgoods.auth.service;

import com.cloudofgoods.auth.entity.Permission;
import com.cloudofgoods.auth.entity.Role;

public interface RoleDetailsService {
    Role assignPermissionToRole(String roleName, Permission permission);

    Role createRole(String roleName);

    int removeRole(String roleName);
}
