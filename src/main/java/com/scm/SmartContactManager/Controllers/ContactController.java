package com.scm.SmartContactManager.Controllers;

import com.scm.SmartContactManager.Entities.Contacts;
import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Services.ContactService;
import com.scm.SmartContactManager.Services.UserService;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.helper.PrincipalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;


//add contact view
@GetMapping("/add")
public String addContactView(Model model){

    ContactForm contactForm=new ContactForm();
    //contactForm.setName("Rahul Bhattacharya");
    //contactForm.setFavorite(true);
    model.addAttribute("contactForm",contactForm); //initially blank data will be passed here in contact form

    return "user/add_contact";
}


@RequestMapping(value = "/add",method = RequestMethod.POST)
public String saveContact(@ModelAttribute ContactForm contactForm, Authentication authentication){
    System.out.println(contactForm);


    String username= PrincipalHelper.getEmailOfLoggedInUser(authentication);
    User user=userService.getUserByEmail(username);

    Contacts contacts=new Contacts();
    contacts.setName(contactForm.getName());
    contacts.setUser(user);
    contacts.setEmail(contactForm.getEmail());
    contacts.setPhoneNumber(contactForm.getPhoneNumber());
    contacts.setAddress(contactForm.getAddress());
    contacts.setDescription(contactForm.getDescription());
    contacts.setFavorite(contactForm.isFavorite());
    contacts.setWebsiteLink(contactForm.getWebsiteLink());
    contacts.setLinkedinLink(contactForm.getLinkedinLink());
    //process form
   contactService.saveContacts(contacts);

    return "redirect:/user/contact/add";
}



}
