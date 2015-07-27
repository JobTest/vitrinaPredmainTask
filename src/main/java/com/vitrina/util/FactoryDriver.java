package com.vitrina.util;

import com.vitrina.util.drivers.JDBCDriver;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alexandr on 17.07.15.
 */
public class FactoryDriver {

    private FactoryDriver(){}

    public static Connection getConnection() throws ExceptionInInitializerError, IOException {
        if( connect == null ) {
            JDBCProperties jdbcProperties = new JDBCProperties("jdbc.properties");

            Driver driverManager = null;
            Properties properties = new Properties();
            properties.setProperty("user", jdbcProperties.property.getProperty("jdbc.username"));
            properties.setProperty("password", jdbcProperties.property.getProperty("jdbc.password"));
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF-8");
            try {
                driverManager = JDBCDriver.getInstance(jdbcProperties.property.getProperty("jdbc.url"), jdbcProperties.property.getProperty("jdbc.driver"), 10, properties);
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
    private static Session    session = null;
}

class JDBCProperties {

    public Properties property = new Properties();

    public JDBCProperties(){}
    public JDBCProperties(String file) {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(file);
            if (inputStream != null)
                this.property.load(inputStream);
            else
                throw new FileNotFoundException("--FileNotFound--");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
