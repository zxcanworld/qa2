package com.example.qa.service.impl;

import com.example.qa.entity.User;
import com.example.qa.repository.UserRepository;
import com.example.qa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setLastName(updatedUser.getLastName());
            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }
}
