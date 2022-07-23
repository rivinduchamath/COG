package com.cloudofgoods.usersservice.repository;



import com.cloudofgoods.usersservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<Users,Integer> {
}
