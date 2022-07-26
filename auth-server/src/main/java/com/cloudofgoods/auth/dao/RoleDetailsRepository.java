package com.cloudofgoods.auth.dao;


import com.cloudofgoods.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleDetailsRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findRoleByName(String userName);

    int deleteRoleByName(String roleName);

/*
    @Modifying
    @Query("delete from Role l where l.name =:roleName")
    void deleteByName(@Param("roleName")String roleName);*/
}
