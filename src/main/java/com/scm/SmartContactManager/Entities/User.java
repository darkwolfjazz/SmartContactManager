package com.scm.SmartContactManager.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

@Id
private String userId;
@Column(name = "user_name",nullable = false)
private String name;
@Column(unique = true,nullable = false)
private String email;
private String password;
@Column(length = 5000)
private String about;
@Column(length = 5000)
private String profilePic;
private String phoneNumber;
private boolean enabled=false;
private boolean emailVerified=false;
private boolean phoneVerified=false;

@Enumerated(value = EnumType.STRING)
private Providers provider = Providers.SELF;
private String providerUserId;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
private List<Contacts> contact=new ArrayList<>();

}
