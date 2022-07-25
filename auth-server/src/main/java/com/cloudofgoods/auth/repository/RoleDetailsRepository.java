package com.cloudofgoods.auth.repository;


import com.cloudofgoods.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDetailsRepository extends JpaRepository<Role,Integer> {

}
