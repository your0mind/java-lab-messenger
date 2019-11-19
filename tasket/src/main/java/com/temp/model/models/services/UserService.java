package com.temp.model.models.services;

import com.temp.model.models.User;

public interface UserService {
    User findUserById(int id);
    User findUserByUsername(String username);
    int saveUser(User user);
}
