package com.scm.SmartContactManager.config;

import com.scm.SmartContactManager.Entities.Providers;
import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Repository.UserRepository;
import com.scm.SmartContactManager.helper.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    Logger logger= LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("Inside OauthAuthenticationSuccessHandler class");

        //identify the provider
        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
       logger.info(authorizedClientRegistrationId);

       var oAuth2User= (DefaultOAuth2User)authentication.getPrincipal();
       oAuth2User.getAttributes().forEach((key,value)->{
           logger.info(key + " : " +value);
       });
       User user=new User();
       user.setUserId(UUID.randomUUID().toString());
       user.setRoleList(List.of(AppConstants.ROLE_USER));
       user.setEmailVerified(true);
       user.setEnabled(true);

       if(authorizedClientRegistrationId.equalsIgnoreCase("google")){

           //this is google case
           user.setEmail(oAuth2User.getAttribute("email").toString());
           user.setProfilePic(oAuth2User.getAttribute("picture").toString());
           user.setName(oAuth2User.getAttribute("name").toString());
           user.setProviderUserId(oAuth2User.getName());
           user.setProvider(Providers.GOOGLE);

       }else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){

           //this is github case
           String email=oAuth2User.getAttribute("email")!=null
                   ? oAuth2User.getAttribute("email").toString()
                   : oAuth2User.getAttribute("login").toString()+"@gmail.com";

           String picture =oAuth2User.getAttribute("avatar_url").toString();
           String name=oAuth2User.getAttribute("login").toString();
           String providerUserId=oAuth2User.getName();
           user.setEmail(email);
           user.setProfilePic(picture);
           user.setName(name);
           user.setProviderUserId(providerUserId);
           user.setProvider(Providers.GITHUB);
       }

        //before redirecting save it in the db
        //DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
//        logger.info(user.getName());
//        user.getAttributes().forEach((key,value)->{
//            logger.info("{} => {} ",key,value);
//        });
//        logger.info(user.getAuthorities().toString());

//        String email = user.getAttribute("email").toString();
//        String name =user.getAttribute("name").toString();
//        String picture =user.getAttribute("picture").toString();
//
//        //create user and save it in db
//        User user1=new User();
//        user1.setEmail(email);
//        user1.setName(name);
//        user1.setProfilePic(picture);
//        user1.setPassword("password");
//        user1.setUserId(UUID.randomUUID().toString());
//        user1.setProvider(Providers.GOOGLE);
//        user1.setEnabled(true);
//        user1.setEmailVerified(true);
//        user1.setProviderUserId(user.getName());
//        user1.setRoleList(List.of(AppConstants.ROLE_USER));
//        user1.setAbout("This account is creating using Oauth google");
//
//
//        User addUserinDB=userRepository.findByEmail(email).orElse(null);
//        if(addUserinDB==null){
//            userRepository.save(user1);
//            logger.info("User successfully saved with google Oauth" + email);
//        }
        User user1=userRepository.findByEmail(user.getEmail()).orElse(null);
        if(user1==null){
            userRepository.save(user);
        }
        response.sendRedirect("/user/profile");
    }
}
