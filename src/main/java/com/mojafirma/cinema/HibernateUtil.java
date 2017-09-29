package com.mojafirma.cinema;

import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;


    static{
        try{
            sessionFactory = new org.hibernate.cfg.Configuration()
                    .configure() // configures settings from hibernate.cfg.xml
                    .buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
