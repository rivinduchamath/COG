package com.cloudofgoods.usersservice.configaration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "payment";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
//                    .antMatchers("/getUser/**").permitAll()
                    .anyRequest().
//            // permitAll();
        fullyAuthenticated();
        }


    }
    static final String[] AUTH_WHITELIST = {
            "/registration/**",
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**",

    };

}