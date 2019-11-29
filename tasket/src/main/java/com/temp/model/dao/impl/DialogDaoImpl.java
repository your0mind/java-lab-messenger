package com.temp.model.dao.impl;

import com.temp.model.dao.DialogDao;
import com.temp.model.models.Dialog;
import com.temp.model.models.User;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DialogDaoImpl implements DialogDao {
    @Override
    public List<Dialog> findAllByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Dialog> dialogs = (List<Dialog>) session
                .createQuery("from Dialog where user1_id =:userId or user2_id =:userId")
                .setParameter("userId", user.getId())
                .list();
        session.close();
        return dialogs;
    }

    @Override
    public int save(Dialog dialog) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(dialog);
        transaction.commit();
        session.close();
        return id;
    }
}
