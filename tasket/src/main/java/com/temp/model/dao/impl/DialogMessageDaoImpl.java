package com.temp.model.dao.impl;

import com.temp.model.dao.DialogMessageDao;
import com.temp.model.models.Dialog;
import com.temp.model.models.DialogMessage;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DialogMessageDaoImpl implements DialogMessageDao {
    @Override
    public DialogMessage find(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        DialogMessage dialogMessage = session.get(DialogMessage.class, id);
        session.close();
        return dialogMessage;
    }

    @Override
    public List<DialogMessage> findAll(Dialog dialog) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<DialogMessage> dialogs = (List<DialogMessage>) session
                .createQuery("from DialogMessage where dialog_id = :dialogId")
                .setParameter("dialogId", dialog.getId())
                .list();
        session.close();
        return dialogs;
    }

    @Override
    public int save(DialogMessage message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(message);
        transaction.commit();
        session.close();
        return id;
    }
}
