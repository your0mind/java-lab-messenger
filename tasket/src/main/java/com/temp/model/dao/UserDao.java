package com.temp.model.dao;

import com.temp.model.models.User;

import java.util.List;

public interface UserDao {
    User find(int id);
    User find(String username);
    List<User> getAllExcept(User user);
    int save(User user);
}
