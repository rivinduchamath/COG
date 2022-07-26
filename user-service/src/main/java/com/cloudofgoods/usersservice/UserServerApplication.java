package com.cloudofgoods.usersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
@EnableTask
public class UserServerApplication  {
//    @Bean
//    RentProcessTaskRunner getRentProcessTaskRunner() {
//        return new RentProcessTaskRunner();
//    }

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication .class, args);
    }

}

