//package usersservice.Configaration;
//
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableResourceServer
//public class CustomAuthorizationServerConfigurer extends
//        AuthorizationServerConfigurerAdapter {
//
//    @Override
//    public void configure(
//            ClientDetailsServiceConfigurer clients
//    ) throws Exception {
//        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//        clients.inMemory()
//                .withClient("mobile")
//                .authorizedGrantTypes("password")
//                .secret("1234")
//                .scopes("all");
//    }
//}