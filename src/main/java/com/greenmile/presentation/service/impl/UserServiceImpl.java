package com.greenmile.presentation.service.impl;

import com.greenmile.presentation.entity.User;
import com.greenmile.presentation.repository.UserRepository;
import com.greenmile.presentation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public void crate(User user) {
        userRepository.save(user);
    }
}
