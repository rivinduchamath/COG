package com.cloudofgoods.auth.repository;

import com.cloudofgoods.auth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDetailsRepository extends JpaRepository<Permission,Integer> {
}
