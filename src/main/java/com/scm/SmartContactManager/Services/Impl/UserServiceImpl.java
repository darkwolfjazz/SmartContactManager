package com.scm.SmartContactManager.Services.Impl;

import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Repository.UserRepository;
import com.scm.SmartContactManager.Services.UserService;
import com.scm.SmartContactManager.helper.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Override
    public User saveUser(User user) {
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       User currentUser=userRepository.findById(user.getUserId()).orElseThrow(()->new RuntimeException("User not Found"));
      currentUser.setName(user.getName());
      currentUser.setEmail(user.getEmail());
      currentUser.setPassword(user.getPassword());
      currentUser.setPhoneNumber(user.getPhoneNumber());
      currentUser.setAbout(user.getAbout());
      currentUser.setProfilePic(user.getProfilePic());
      currentUser.setEnabled(user.isEnabled());
      currentUser.setEmailVerified(user.isEmailVerified());
      currentUser.setPhoneVerified(user.isPhoneVerified());
      currentUser.setProvider(user.getProvider());
      currentUser.setProviderUserId(user.getProviderUserId());
      User save= userRepository.save(currentUser);
      return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User currentUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not Found"));
        userRepository.deleteById(id);
    }

    @Override
    public boolean ifUserExists(String userId) {
    User currentUser=userRepository.findById(userId).orElse(null);
    return currentUser != null ? true :false;
    }

    @Override
    public boolean ifUserExistsByEmail(String email) {
        User user=userRepository.findByEmail(email).orElse(null);
        return user!=null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return  userRepository.findByEmail(email).orElse(null);
    }
}
