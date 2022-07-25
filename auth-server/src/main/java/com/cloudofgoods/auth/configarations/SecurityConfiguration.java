package com.cloudofgoods.auth.configarations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean("cogAuthManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean ();
    }

    @Order(1)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //configure your path here
        //I purposly configured GET user to
        // permit all to see diference
        //for example
        // @formatter:off
        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/user")
//                .permitAll()
//                .antMatchers("/greeting")
//                .fullyAuthenticated().and()
                .csrf().disable();
        // @formatter:on
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}