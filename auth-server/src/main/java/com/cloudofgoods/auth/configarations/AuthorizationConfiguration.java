//package com.cloudofgoods.auth.configarations;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.sql.DataSource;
//
//@Configuration
//@RequiredArgsConstructor
//public class AuthorizationConfiguration implements AuthorizationServerConfigurer {
//
//    final PasswordEncoder passwordEncoder;
//    final AuthenticationManager authenticationManager;
//    final DataSource dataSource;
//    @Bean
//    TokenStore jdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
//
//        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
//
//        authorizationServerSecurityConfigurer.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
//      // oauthServer.realm("sparklr2/client");
//      //  authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
////        authorizationServerSecurityConfigurer.tokenKeyAccess("isAnonymous()");
//
//
//    }
//
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
//        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
//        clientDetailsServiceConfigurer.jdbc(dataSource).passwordEncoder(passwordEncoder);
//    }
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
//        authorizationServerEndpointsConfigurer.tokenStore(jdbcTokenStore());
//        authorizationServerEndpointsConfigurer.authenticationManager(authenticationManager);
//    }
//}
