package com.temp.model.services;

import com.temp.model.models.User;

import java.util.List;

public interface UserService {
    User findUserById(int id);
    User findUserByUsername(String username);
    List<User> getAllUsers();
    int saveUser(User user);
}
