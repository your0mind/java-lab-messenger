package com.temp.model.dao;

import com.temp.model.models.User;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);
    int save(User user);
}
