package com.vitrina.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexandr on 17.06.15.
 */
public interface Driver {

    public Connection getConnection() throws SQLException;

}