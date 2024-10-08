package com.scm.SmartContactManager.Controllers;

import com.scm.SmartContactManager.helper.PrincipalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {



    private Logger logger= LoggerFactory.getLogger(UserController.class);


    //user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping("/profile")
    public String userProfile(Authentication authentication){
       String username= PrincipalHelper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in :{}" ,username);
        return "user/profile";
    }





}
