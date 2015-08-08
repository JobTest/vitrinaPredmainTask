package com.vitrina.util;

import java.sql.SQLException;

/**
* Created by alexandr on 17.06.15.
*/
public interface Driver {

    public <T> T getConnection() throws SQLException; //public Connection getConnection() throws SQLException;

}