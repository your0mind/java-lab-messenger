package com.temp.model.services.impl;

import com.temp.model.dao.UserDao;
import com.temp.model.models.User;
import com.temp.model.dao.impl.UserDaoImpl;
import com.temp.model.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao usersDao = new UserDaoImpl();

    @Override
    public User findUser(int id) {
        return usersDao.find(id);
    }

    @Override
    public User findUser(String username) {
        return usersDao.find(username);
    }

    @Override
    public List<User> getAllUsersExcept(User user) {
        return usersDao.getAllExcept(user);
    }

    @Override
    public int saveUser(User user) {
        return usersDao.save(user);
    }
}
