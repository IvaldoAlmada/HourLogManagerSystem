package com.greenmile.presentation.service;

import com.greenmile.presentation.entity.User;

public interface UserService {

    Iterable<User> getAllUsers();

    void crate(User user);
}
