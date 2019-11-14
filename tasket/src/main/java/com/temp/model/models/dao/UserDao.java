package com.temp.model.models.dao;

import com.temp.model.models.User;

public interface UserDao {
    User findById(int id);
    User findByUsername(String username);
    void save(User user);
}
