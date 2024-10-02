package com.scm.SmartContactManager.Services.Impl;

import com.scm.SmartContactManager.Entities.User;
import com.scm.SmartContactManager.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public boolean ifUserExists(String userId) {
        return false;
    }

    @Override
    public boolean ifUserExistsByEmail(String email) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
