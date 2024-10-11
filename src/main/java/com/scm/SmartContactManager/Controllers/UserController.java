package com.scm.SmartContactManager.Controllers;

import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Services.UserService;
import com.scm.SmartContactManager.helper.PrincipalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);





    //user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    //user profile page
    @RequestMapping("/profile")
    public String userProfile(Model model,Authentication authentication){

        return "user/profile";
    }





}
