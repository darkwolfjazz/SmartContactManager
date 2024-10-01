package com.scm.SmartContactManager.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
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

private Providers provider = Providers.SELF;
private String providerUserId;



}
