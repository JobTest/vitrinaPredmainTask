package com.vitrina.util;

import com.vitrina.util.drivers.PoolDriver;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alexandr on 17.07.15.
 */
public class FactoryDriver {
    private static final String     USER = "root";
    private static final String PASSWORD = "1978";
    private static final String      URL = "jdbc:mysql://localhost:3306/vitrina";

    private FactoryDriver(){}

    public static Connection getConnection() throws ExceptionInInitializerError {
        if( connect == null ) {
            Driver driverManager = null;
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF-8");
            try {
                driverManager = PoolDriver.getInstance(URL, "com.mysql.jdbc.Driver", 10, properties);
                connect = driverManager.getConnection(); //connect = DriverManager.getConnection(URL, properties);
            } catch (SQLException e) { System.err.print("-SQLException-"); }
        }
        return connect;
    }
    public static EntityManager getEntityManager() {
        return em == null ? em = Persistence.createEntityManagerFactory("Issue2").createEntityManager() : em;
    }
    public static Session getSession() { //public static SessionFactory getSessionFactory() {
        return session == null ? session = new Configuration().configure("hibernate.cft.xml").buildSessionFactory().openSession() : session;
    }

    private static Connection connect = null;
    private static EntityManager   em = null;
    private static Session    session = null; //private static SessionFactory sessionFactory = null;
}
