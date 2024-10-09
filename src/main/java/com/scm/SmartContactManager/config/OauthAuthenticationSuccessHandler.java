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

        //before redirecting save it in the db
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
//        logger.info(user.getName());
//        user.getAttributes().forEach((key,value)->{
//            logger.info("{} => {} ",key,value);
//        });
//        logger.info(user.getAuthorities().toString());

        String email = user.getAttribute("email").toString();
        String name =user.getAttribute("name").toString();
        String picture =user.getAttribute("picture").toString();

        //create user and save it in db
        User user1=new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("This account is creating using Oauth google");


        User addUserinDB=userRepository.findByEmail(email).orElse(null);
        if(addUserinDB==null){
            userRepository.save(user1);
            logger.info("User successfully saved with google Oauth" + email);
        }
        response.sendRedirect("/user/profile");



    }
}
