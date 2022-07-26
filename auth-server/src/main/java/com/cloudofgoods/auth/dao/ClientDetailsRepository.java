package com.cloudofgoods.auth.dao;

import com.cloudofgoods.auth.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientDetailsRepository extends JpaRepository<OauthClientDetails,String> {
}
