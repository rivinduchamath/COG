package com.cloudofgoods.usersservice.repository;



import com.cloudofgoods.usersservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<User,Long> {
}
