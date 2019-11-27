package com.temp.model.dao;

import com.temp.model.models.User;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);
    List<User> getAll();
    int save(User user);
}
