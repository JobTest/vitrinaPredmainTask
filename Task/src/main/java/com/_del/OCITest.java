//package com.oracle;
//
//import java.sql.*;
//import java.util.Locale;
//import java.util.Properties;
//
///**
// * Created by alexandr on 07.09.15.
// * @author administrator
// * {@link https://community.oracle.com/thread/430906}
// * **************************************************
// * java.sql.SQLException: ORA-00604: error occurred at recursive SQL level 1 ORA-12705: Cannot access NLS data files or invalid environment specified
// */
//public class OCITest {
//
//    /**
//     * @param args
//     */
////    public static void main(String[] args) throws ClassNotFoundException {
////        Class.forName("oracle.jdbc.driver.OracleDriver");
////
////        Connection con = null;
////        CallableStatement cstmt = null;
////
////        String url = "jdbc:oracle:oci8:@GPSD1.IN.IBM.COM";
////        String userName = "SYSTEM"; //String userName = "swbapps";
////        String password = "oracle"; //String password = "swab";
////
////        try
////        {
////            System.out.println("Registering Driver ...");
////            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
////
////            System.out.println("Creating Connection ...");
////            con = DriverManager.getConnection(url, userName, password);
////
////            System.out.println("Success.");
////
////        } catch(Exception ex) {
////            ex.printStackTrace(System.err);
////        } finally {
////            if(cstmt != null) try{cstmt.close();}catch(Exception _ex){}
////            if(con != null) try{con.close();}catch(Exception _ex){}
////        }
////    }
//    public static void main(String[] args) {
//        conection();
//    }
//
//    public static void conection() {
//        String user = "SYSTEM";
//        String password = "oracle";
//        String url="jdbc:oracle:thin:@//10.13.71.34:1521/XE";
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
//            Properties connAttr = new Properties();
//
//            Properties props = new Properties();
//
//            connAttr.put("USER",user);
//            connAttr.put("PASSWORD",password);
//            connAttr.put("charSet", "UTF-8");
//
//            Locale.setDefault(new Locale("es","ES"));
//
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//            Connection conn = DriverManager.getConnection(url,user,password);
//
//            ////////////////////////////
//            String sql_req = "SELECT * FROM HELP"; //String sql_req = "SELECT * FROM EMPLOYEES";
//            Statement stat = conn.createStatement();
//
//            ResultSet rs = stat.executeQuery(sql_req);
//
//            while (rs.next()) {
////                System.out.println(rs.getString("LAST_NAME"));
//                System.out.println(rs.getString("TOPIC") + "   " + rs.getString("SEQ") + "   " + rs.getString("INFO"));
//            }
//            ////////////////////////////
//            conn.close();
//        } catch (Exception exc) {
//            System.out.println("We have encountered an error"+" : "+exc);
//            exc.printStackTrace();
//        }
//    }
//
//}
