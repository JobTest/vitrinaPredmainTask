package com.vitrina.util.drivers;

import com.vitrina.util.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by alexandr on 17.06.15.
 */
public class BaseDriver implements Driver {

    String url;
    String name;
    String passwd;

    public BaseDriver(){}
    public BaseDriver(String url, String name, String passwd){
        this.url = url;
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, passwd);
    }
}