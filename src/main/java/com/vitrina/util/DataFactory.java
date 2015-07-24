package com.vitrina.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alexandr on 17.07.15.
 */
public class DataFactory {

    private static Connection connect = null;
    private static String        USER = "root";
    private static String    PASSWORD = "1111";
    private static String         URL = "jdbc:mysql://localhost:3306/vitrina";

    private DataFactory(){}

    public static Connection getInstance() throws ExceptionInInitializerError {
        if( connect == null ){
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } catch(ClassNotFoundException e){
                System.err.print("Driver not found");
                System.exit(0);
            }

            try {
                Properties properties = new Properties();
                properties.setProperty("user", USER);
                properties.setProperty("password", PASSWORD);
                properties.setProperty("useUnicode", "true");
                properties.setProperty("characterEncoding", "UTF-8");
                connect = DriverManager.getConnection(URL, properties);
            } catch(SQLException e){
                System.err.print("-SQLException-");
            }
        }
        return connect;
    }

}
