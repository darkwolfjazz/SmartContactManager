package com.scm.SmartContactManager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

@NotBlank(message = "Name cannot be blank")
private String name;
@Email(message = "Please enter a valid email")
@NotBlank(message = "Email is required")
private String email;
@NotBlank(message = "Please enter a password")
@Size(min = 6, message = "Min 6 Characters is required")
private String password;
@NotBlank(message = "Please enter a valid phone number")
@Size(min = 0,max = 10,message = "Invalid Phone Number")
private String phoneNumber;
@NotBlank(message = "Please enter about")
private String about;
}
