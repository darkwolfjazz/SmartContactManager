package com.scm.SmartContactManager.Services;

import com.scm.SmartContactManager.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean ifUserExists(String userId);
    boolean ifUserExistsByEmail(String email);
    List<User>getAllUsers();
}
