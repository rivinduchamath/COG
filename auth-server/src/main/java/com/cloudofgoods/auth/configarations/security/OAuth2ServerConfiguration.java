package com.cloudofgoods.auth.configarations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

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
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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
            http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().
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
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final DataSource dataSource;
        private final AuthenticationManager authenticationManager;

////////////////////////////////////////////////////////////
@Autowired
private UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;
        @Bean
        public JwtAccessTokenConverter tokenEnhancer() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("1234");
            return converter;
        }

   /*     @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(tokenEnhancer());
        }
*/
        @Bean
        public TokenStore tokenStore() {

            return new JwtTokenStore(tokenEnhancer());
        }

        /////////////////////////////////////////////////
        public AuthorizationServerConfiguration(@Qualifier("cogAuthManager") AuthenticationManager authenticationManager,
                                                DataSource dataSource, PasswordEncoder passwordEncoder) {
            this.authenticationManager = authenticationManager;
            this.dataSource = dataSource;
            this.passwordEncoder = passwordEncoder;
        }

    /*    @Bean
        TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }*/

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