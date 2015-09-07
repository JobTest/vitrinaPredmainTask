package com.vitrina.oracle.windows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

/**
 * Created by Саша on 07.09.2015.
 * @author alexandr
 * {@link https://community.oracle.com/thread/430906}
 ** {@link http://www.wikiguga.com/topic/08dbc5d44687e108ecd8e48455c4ca73}
 ** {@link http://www.sql.ru/forum/864343/ibm-rad-proekt-jpa-i-konnekt-k-oracle-10g-kak}
 * *************************************************************************************
 * ORA-00604: error occurred at recursive SQL level 1
 * ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * VM options: -Duser.language=en -Duser.region=us
 */
public class JDBCConnect3 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.US);

            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String      sql = "SELECT * FROM ISSUE";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next())
                System.out.println(rs.getString("ID")
                        + " | " + rs.getString("PARENT_ID")
                        + " | " + rs.getString("PROJECT_ID")
                        + " | " + rs.getString("PROJECT_NAME")
                        + " | " + rs.getString("TRACKER_ID")
                        + " | " + rs.getString("TRACKER_NAME")
                        + " | " + rs.getString("FIXED_VERSION_ID")
                        + " | " + rs.getString("FIXED_VERSION_NAME")
                        + " | " + rs.getString("STATUS_NAME")
                        + " | " + rs.getString("STATUS_NAME")
                        + " | " + rs.getString("SUBJECT")
                        + " | " + rs.getString("START_DATE")
                        + " | " + rs.getString("DUE_DATE"));

            conn.close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }

    public static String      URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
    public static String     USER = "HR";
    public static String PASSWORD = "hr";
}
