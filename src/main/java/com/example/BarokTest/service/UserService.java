package com.example.BarokTest.service;

import com.example.BarokTest.entity.User;
import com.example.BarokTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user) {
        return userRepository.add(user);
    }
}
