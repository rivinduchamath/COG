package com.cloudofgoods.auth.dao;

import com.cloudofgoods.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionDetailsRepository extends JpaRepository<Permission,Integer> {

    Optional<Permission> findPermissionByName(String permission);

    boolean deletePermissionById(Integer permissionId);
}
