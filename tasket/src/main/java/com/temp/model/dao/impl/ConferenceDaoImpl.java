package com.temp.model.dao.impl;

import com.temp.model.dao.ConferenceDao;
import com.temp.model.models.Conference;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ConferenceDaoImpl implements ConferenceDao {
    @Override
    public Conference find(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Conference conference = session.get(Conference.class, id);
        session.close();
        return conference;
    }

    @Override
    public int save(Conference conference) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(conference);
        transaction.commit();
        session.close();
        return id;
    }
}
