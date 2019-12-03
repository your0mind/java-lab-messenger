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
    public Dialog find(User user1, User user2) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Dialog dialog = (Dialog) session
                .createQuery("from Dialog where user1_id = :user1Id and user2_id = :user2Id " +
                        "or user1_id = :user2Id and user2_id = :user1Id")
                .setParameter("user1Id", user1.getId())
                .setParameter("user2Id", user2.getId())
                .uniqueResult();
        session.close();
        return dialog;
    }

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
