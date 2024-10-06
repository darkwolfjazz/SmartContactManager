package com.scm.SmartContactManager.config;

import com.scm.SmartContactManager.Services.Impl.SecurityCustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//@Bean
//public UserDetailsService userDetailsService(){
//   UserDetails user= User
//           .withDefaultPasswordEncoder()
//           .username("admin123")
//           .password("admin123")
//           .build();
//    UserDetails user2= User
//            .withUsername("user2")
//            .password("user2")
//            .build();
//    return new InMemoryUserDetailsManager(user,user2);
//}
    @Autowired
    private SecurityCustomUserServiceImpl userDetailService;


    //configuration to restrict/permit the endpoints

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize->{
//            authorize.requestMatchers("/about","/signup","/do-register").permitAll();
             authorize.requestMatchers("/user/**").authenticated();
              authorize.anyRequest().permitAll();
        });

        //form default login
        //in case if you want to change anything regarding form login come here
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


    //configuration of authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        //user detail service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

     //configuration to encode the password
      @Bean
      public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
      }


}
