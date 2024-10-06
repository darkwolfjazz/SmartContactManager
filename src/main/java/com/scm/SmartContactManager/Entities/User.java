package com.scm.SmartContactManager.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

@Id
private String userId;
@Column(name = "user_name",nullable = false)
private String name;
@Column(unique = true,nullable = false)
private String email;
@Getter(AccessLevel.NONE)
private String password;
@Column(length = 5000)
private String about;
@Column(length = 5000)
private String profilePic;
private String phoneNumber;

@Getter(value = AccessLevel.NONE)
private boolean enabled=true;
private boolean emailVerified=true;
private boolean phoneVerified=false;

@Enumerated(value = EnumType.STRING)
private Providers provider = Providers.SELF;
private String providerUserId;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
private List<Contacts> contact=new ArrayList<>();

   //very important concept on @ElementCollection annotation
   @ElementCollection(fetch = FetchType.EAGER)
   private List<String>roleList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      //List of role [USER,ADMIN]
        Collection<SimpleGrantedAuthority>roles=roleList.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    //for this project jo humara email hai wohi humara username hai

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
