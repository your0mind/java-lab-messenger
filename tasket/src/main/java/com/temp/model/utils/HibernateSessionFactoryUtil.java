package com.temp.model.utils;

import com.temp.model.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.*;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private final static Logger logger = Logger.getLogger(HibernateSessionFactoryUtil.class.getSimpleName());

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Dialog.class);
                configuration.addAnnotatedClass(DialogMessage.class);
                configuration.addAnnotatedClass(Conference.class);
                configuration.addAnnotatedClass(ConferenceParticipant.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return sessionFactory;
    }
}