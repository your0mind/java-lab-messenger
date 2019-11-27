package com.temp.model.services.impl;

import com.temp.model.dao.UserDao;
import com.temp.model.models.User;
import com.temp.model.dao.impl.UserDaoImpl;
import com.temp.model.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao usersDao = new UserDaoImpl();

    @Override
    public User findUserById(int id) {
        return usersDao.findById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.getAll();
    }

    @Override
    public int saveUser(User user) {
        return usersDao.save(user);
    }
}
