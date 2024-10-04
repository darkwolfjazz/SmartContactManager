package com.scm.SmartContactManager.Controllers;

import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Services.UserService;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.helper.Message;
import com.scm.SmartContactManager.helper.MessageTypeEnum;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class TestController {

    @Autowired
    private UserService userService;


@GetMapping("/test")
    public String test(){
    return "Test Controller is working fine";
}

@GetMapping("/home")
public String home(Model model) {
    //model.addAttribute("page","home");
    return "home";
}

@GetMapping("/about")
    public String aboutPage(Model model){
    System.out.println("About Page loading!");
    //model.addAttribute("page","about");
    return "about";
}
@GetMapping("/service")
public String servicePage(Model model){
    System.out.println("Service Page loading!");
    //model.addAttribute("page","service");
    return "service";
}

@GetMapping("/contact")
    public String contactPage(Model model){
    System.out.println("Contact Page loading");
    //model.addAttribute("page","contact");
    return "contact";
}
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/signup")
    public String singupPage(Model model){
        UserForm userForm=new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    //Processing signup of user
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult result, HttpSession session){
        System.out.println("Inside Process Registration");
        System.out.println(userForm);
        //Validation ----
        if(result.hasErrors()){
            return "register";
        }

        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        User savedUser= userService.saveUser(user);
        System.out.println("User is saved");
        //adding message upon signup

        Message message=Message.builder().content("Registration successful !").type(MessageTypeEnum.blue).build();


        session.setAttribute("message",message);
        return "redirect:/signup";
    }





}
