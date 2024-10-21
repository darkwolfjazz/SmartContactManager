package com.scm.SmartContactManager.forms;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedinLink;
    private MultipartFile picture;




}
