package com.vitrina.util;

import com.vitrina.util.drivers.BaseDriver;
import com.vitrina.util.drivers.ContextDriver;
import com.vitrina.util.drivers.PoolDriver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alexandr on 17.07.15.
 */
public class FactoryDriver {

    private static Connection    connect = null;
    private static final String     USER = "root";
    private static final String PASSWORD = "1111";
    private static final String      URL = "jdbc:mysql://localhost:3306/vitrina";

    private FactoryDriver(){}

    public static Connection getInstance(Drivers type) throws ExceptionInInitializerError {
        if( connect == null ){
            Driver driverManager = null;
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF-8");
            try {
                switch (type){
                    case BASE:
                        driverManager = new BaseDriver(URL, USER, PASSWORD);
                        break;
                    case POOL:
                        driverManager = PoolDriver.getInstance(URL, "com.mysql.jdbc.Driver", 10, properties);
                        break;
                    case CONTEXT:
                        driverManager = new ContextDriver();
                        break;
                }
                connect = driverManager.getConnection(); //connect = DriverManager.getConnection(URL, properties);
            } catch(SQLException e){ System.err.print("-SQLException-"); }
        }
        return connect;
    }
}
