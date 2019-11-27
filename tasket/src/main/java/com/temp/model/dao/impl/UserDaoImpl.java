package com.temp.model.dao.impl;


import com.temp.model.dao.UserDao;
import com.temp.model.models.User;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = (User) session
                .createQuery("from User where username =:username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) session
                .createQuery("from User")
                .list();
        session.close();
        return users;
    }

    @Override
    public int save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        return id;
    }
}
