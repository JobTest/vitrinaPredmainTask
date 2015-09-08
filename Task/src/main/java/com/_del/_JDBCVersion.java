//package com.oracle;
//
//import oracle.jdbc.pool.OracleDataSource;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// * Created by alexandr on 07.09.15.
// *
// * {@link http://forum.sources.ru/index.php?showtopic=201692&view=showall}
// */
//
//class JDBCVersion{
//    public static void main (String args[]) throws SQLException, ClassNotFoundException {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//
//        OracleDataSource ods = new OracleDataSource();
//        ods.setURL("jdbc:oracle:thin:@//10.13.71.34:1521/XE"); //ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
//        Connection conn = ods.getConnection("SYSTEM", "oracle");
////        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@*.*.*.*:1251:*", "*", "*" );
//
////        OracleDataSource ds = new OracleDataSource();
////        ds.setDriverType("thin");
////        ds.setServerName("10.13.71.34");
////        ds.setPortNumber(1521);
////        ds.setDatabaseName("XE"); // sid
////        ds.setUser("SYSTEM"); //ds.setUser("hr");
////        ds.setPassword("oracle"); //ds.setPassword("hr");
////        Connection conn = ds.getConnection();
//
////        String url = "jdbc:oracle:thin:@//10.13.71.34:1521/XE"; //String url = "jdbc:oracle:thin:@//10.13.71.34:1521/XE";
////        Connection conn = DriverManager.getConnection(url, "SYSTEM", "oracle");
//
//
//        /* Create Oracle DatabaseMetaData object */
//        DatabaseMetaData meta = conn.getMetaData();
//
//        /* gets driver info: */
//        System.out.println("JDBC driver version is " + meta.getDriverVersion());
//    }
//}
