package com.temp.model.dao.impl;

import com.temp.model.dao.ConferenceParticipantDao;
import com.temp.model.models.*;
import com.temp.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class ConferenceParticipantDaoImpl implements ConferenceParticipantDao {
    @Override
    public List<ConferenceParticipant> findAllByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<ConferenceParticipant> participations = (List<ConferenceParticipant>) session
                .createQuery("from ConferenceParticipant where user_id = :userId")
                .setParameter("userId", user.getId())
                .list();
        session.close();
        return participations;
    }
}
