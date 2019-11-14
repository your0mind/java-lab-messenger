package com.temp.model.models.services;

import com.temp.model.models.User;
import com.temp.model.models.dao.UserDao;
import com.temp.model.models.dao.UserDaoImpl;

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
    public void saveUser(User user) {
        usersDao.save(user);
    }
}
