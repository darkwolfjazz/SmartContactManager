package com.scm.SmartContactManager.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

@Id
private String id;
private String name;
private String email;
private String phoneNumber;
private String address;
private String picture;
@Column(length = 5000)
private String description;
private boolean favorite=false;
private String websiteLink;
private String LinkedinLink;
//private List<String>SocialLink=new ArrayList<>();
@ManyToOne
private User user;
@OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
private List<SocialLink> links=new ArrayList<>();


}
