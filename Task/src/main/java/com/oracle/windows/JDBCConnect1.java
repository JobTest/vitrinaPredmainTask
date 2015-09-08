package com.oracle.windows;

import java.sql.*;
import java.util.Locale;

/**
 * Created by alexandr on 07.09.15.
 * @author alexandr
 * {@link https://community.oracle.com/thread/430906}
 * {@link http://www.wikiguga.com/topic/08dbc5d44687e108ecd8e48455c4ca73}
 * {@link http://www.sql.ru/forum/864343/ibm-rad-proekt-jpa-i-konnekt-k-oracle-10g-kak}
 * ************************************************************************************
 * java.sql.SQLException: ORA-00604: error occurred at recursive SQL level 1 ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * ORA-00604: error occurred at recursive SQL level 1
 * ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * VM options: -Duser.language=en -Duser.region=us
 */
public class JDBCConnect1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.US); //Locale.setDefault(new Locale("es","ES"));

            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String      sql = "SELECT * FROM HELP";
            Statement  stat = conn.createStatement();
            ResultSet    rs = stat.executeQuery(sql);

            while (rs.next())
                System.out.println(rs.getString("TOPIC") + "   " + rs.getString("SEQ") + "   " + rs.getString("INFO"));

            conn.close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }

    public static String      URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
    public static String     USER = "SYSTEM";
    public static String PASSWORD = "oracle2";
}
