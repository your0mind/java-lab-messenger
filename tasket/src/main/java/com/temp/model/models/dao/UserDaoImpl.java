package com.temp.model.models.dao;


import com.temp.model.models.User;
import com.temp.model.models.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    @Override
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from users where username =:username")
                .setParameter("username", username);
        return (User) query.uniqueResult();
    }

    @Override
    public int save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        int id = (int) session.save(user);
        tx1.commit();
        session.close();

        return id;
    }
}
