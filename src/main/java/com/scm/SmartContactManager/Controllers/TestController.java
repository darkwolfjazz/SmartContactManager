package com.scm.SmartContactManager.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestController {

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
    public String singupPage(){
        System.out.println("Contact Page loading");
        return "register";
    }



}
