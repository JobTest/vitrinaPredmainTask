//package com.vitrina.oracle;
//
//import oracle.jdbc.pool.OracleDataSource;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// * Created by alexandr on 07.09.15.
// *
// * {@link http://forum.sources.ru/index.php?showtopic=201692&view=showall}
// */
//public class ExampleConnect {
//
//    public static void main(String[] args) throws SQLException {
//        String myURL = "jdbc:oracle:thin:@//10.13.71.34:1521/XE"; //String myURL = "jdbc:oracle:thin:@xe"; //String myURL = "jdbc:oracle:thin:@hr";
//        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
//        ds.setURL(myURL);
//        ds.setServerName("10.13.71.34");
//        Connection conn = ds.getConnection("SYSTEM", "oracle");
//
//        String sql_req = "SELECT * FROM EMPLOYEES";
//        Statement stat = conn.createStatement();
//
//        ResultSet rs = stat.executeQuery(sql_req);
//
//        while (rs.next()) {
//            System.out.println(rs.getString("LAST_NAME"));
//        }
//
//        conn.close();
//    }
//
//}
