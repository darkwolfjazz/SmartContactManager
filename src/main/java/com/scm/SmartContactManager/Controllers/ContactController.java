package com.scm.SmartContactManager.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class ContactController {


//add contact view
@GetMapping("/add")
public String addContactView(Model model){
    return "user/add_contact";
}




}
