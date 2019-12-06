package com.temp.model.dao.impl;

import com.temp.model.dao.ConferenceDao;
import com.temp.model.models.Conference;
import com.temp.model.models.ConferenceParticipant;
import com.temp.model.models.User;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ConferenceDaoImpl implements ConferenceDao {
    @Override
    public Conference find(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Conference conference = session.get(Conference.class, id);
        session.close();
        return conference;
    }

    @Override
    public Conference find(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Conference conference = (Conference) session
                .createQuery("from Conference where name = :name")
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return conference;
    }

    @Override
    public List<Conference> findAllByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<ConferenceParticipant> participants = (List<ConferenceParticipant>) session
                .createQuery("from ConferenceParticipant where user_id = :userId")
                .setParameter("userId", user.getId())
                .list();
        session.close();
        return participants.stream()
                .map(p -> find(p.getConferenceId()))
                .collect(Collectors.toList());
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
