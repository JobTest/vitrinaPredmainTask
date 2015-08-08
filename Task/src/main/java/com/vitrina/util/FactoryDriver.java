package com.vitrina.util;

import com.vitrina.util.drivers.JDBCDriver;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.sql.DataSource;
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


    public static Connection getConnection(String dbConfig) throws ExceptionInInitializerError, IOException {
        if( connect == null ) {
            Driver driverManager = null;
            Properties properties = new Properties();
            JDBCProperties jdbcProperties = new JDBCProperties(dbConfig);
            properties.setProperty("user", jdbcProperties.property.getProperty("jdbc.username"));
            properties.setProperty("password", jdbcProperties.property.getProperty("jdbc.password"));
            properties.setProperty("useUnicode", jdbcProperties.property.getProperty("jdbc.useUnicode"));
            properties.setProperty("characterEncoding", jdbcProperties.property.getProperty("jdbc.characterEncoding"));
            try {
                driverManager = JDBCDriver.getInstance(jdbcProperties.property.getProperty("jdbc.url"), jdbcProperties.property.getProperty("jdbc.driver"), 10, properties);
                /* Эта функция забирает из availableConns очередной Connection и добавляет его в usedConns, затем возвращает это соединение, тем самым он становится используемым: */
                connect = driverManager.getConnection(); //connect = DriverManager.getConnection(URL, properties);
            } catch (SQLException e) { System.err.print("-SQLException-"); }
        }
        return connect;
    }
    public static EntityManager getEntityManager(String dbConfig) {
        return em == null ? em = Persistence.createEntityManagerFactory(dbConfig).createEntityManager() : em;
    }
    public static SessionFactory getFactory(String dbConfig) {
        return factory == null ? factory = new Configuration().configure(dbConfig).buildSessionFactory() : factory;
    }
    public static DataSource getDataSource(DataSource dataSource){
        // > http://javatalks.ru/topics/25965
        // > http://www.mkyong.com/spring/maven-spring-jdbc-example/
        //   http://www.tutorialspoint.com/spring/spring_jdbc_example.htm
        return dataSource;
    }

    private static Connection connect = null;
    private static EntityManager   em = null;
    private static SessionFactory factory = null; //private static Session    session = null;
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
                throw new FileNotFoundException("[FileNotFound] ");
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