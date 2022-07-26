package com.cloudofgoods.auth.configarations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

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
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers("/registration/**").permitAll()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/swagger-ui.html/**").permitAll()
                    .antMatchers("/v2/api-docs/**").permitAll()
//                    .antMatchers("/getUser/**").permitAll()
                    .anyRequest().
            // permitAll();
                 fullyAuthenticated();
        }


    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        private final PasswordEncoder passwordEncoder;
        private final DataSource dataSource;
        private final AuthenticationManager authenticationManager;


        public AuthorizationServerConfiguration(@Qualifier("cogAuthManager") AuthenticationManager authenticationManager, DataSource dataSource, PasswordEncoder passwordEncoder) {
            this.authenticationManager = authenticationManager;
            this.dataSource = dataSource;
            this.passwordEncoder = passwordEncoder;
        }

        @Bean
        TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
            endpoints
                    // Here you can override the default endpoints mappings
                    .pathMapping("/oauth/check_token", "/api/v1/authorize")
                    .pathMapping("/oauth/token", "/api/v1/token")

                    // rest of the authorization server customization
                    .authenticationManager(authenticationManager)
                    .tokenStore(tokenStore());
        }


        @Override
        public void configure(AuthorizationServerSecurityConfigurer security)  {
            security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore());
            return tokenServices;
        }

    }

}