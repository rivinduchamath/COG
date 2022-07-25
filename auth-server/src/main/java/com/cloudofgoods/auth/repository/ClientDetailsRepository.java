package com.cloudofgoods.auth.repository;

import com.cloudofgoods.auth.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientDetailsRepository extends JpaRepository<OauthClientDetails,String> {
}
