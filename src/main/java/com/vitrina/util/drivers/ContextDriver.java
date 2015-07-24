package com.vitrina.util.drivers;

import com.vitrina.util.Driver;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexandr on 17.06.15.
 */
public class ContextDriver implements Driver {

    @Override
    public Connection getConnection() throws SQLException {
        DataSource ds = null;

        try {
            InitialContext initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/dbconnect");
        } catch (NamingException ne) { System.err.println("ERR: NamingException"); }

        return ds.getConnection();
    }
}
