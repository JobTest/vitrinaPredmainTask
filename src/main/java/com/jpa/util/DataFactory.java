package com.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by alexandr on 23.07.15.
 */
public class DataFactory {

    private static SessionFactory sessionFactory;

    private DataFactory(){}

    public static SessionFactory getInstance() throws ExceptionInInitializerError {
        return sessionFactory == null ? sessionFactory = new Configuration().configure("hibernate.cft.xml").buildSessionFactory() : sessionFactory;
    }

}
