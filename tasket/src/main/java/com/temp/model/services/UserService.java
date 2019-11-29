package com.temp.model.services;

import com.temp.model.models.User;

import java.util.List;

public interface UserService {
    User findUser(int id);
    User findUser(String username);
    List<User> getAllUsersExcept(User user);
    int saveUser(User user);
}
