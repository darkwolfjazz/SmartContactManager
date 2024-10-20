package com.scm.SmartContactManager.Controllers;

import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Services.UserService;
import com.scm.SmartContactManager.helper.PrincipalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {


    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public void addLoggedInUser(Model model, Authentication authentication) {
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in user information to model");
        String username = PrincipalHelper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in :{}", username);
        User user = userService.getUserByEmail(username);//username is our email only
        System.out.println(user);
        if(user!=null) {
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user);
        }else{
            System.out.println("User not found");
        }
    }

}
