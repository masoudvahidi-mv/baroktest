package com.example.BarokTest.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GeneralDao {

    private static GeneralDao instance;

    public static GeneralDao getInstance() {
        if (instance == null) {
            instance = new GeneralDao();
        }
        return instance;
    }

    private static final ThreadLocal<Session> currentSession = new ThreadLocal<>();
    private static SessionFactory sessionFactory;

    public static Session getCurrentSession() {
        Session session = currentSession.get();
        if (session == null) {
            session = getNewSession();
            currentSession.set(session);
        }
        return session;
    }

    public static Session getNewSession() {
        return sessionFactory.openSession();
    }

    public void beginTransaction() {
        Session session = getCurrentSession();
        session.beginTransaction();
    }

    public void commit() {
        Session session = currentSession.get();
        if (session != null) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    public void rollBack() {
        Session session = currentSession.get();
        if (session != null) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void close() {
        Session session = currentSession.get();
        currentSession.remove();
        if (session != null) {
            session.close();
        }
    }

    public void endTransaction() {
        commit();
        close();
    }
}
