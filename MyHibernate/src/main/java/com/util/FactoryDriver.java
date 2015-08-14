package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by alexandr on 13.08.15.
 */
public class FactoryDriver {

    private static SessionFactory sessionFactory;

    private FactoryDriver(){}

    public static SessionFactory getSessionFactory(){
        return sessionFactory == null ? sessionFactory = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory() : sessionFactory;
    }

}
