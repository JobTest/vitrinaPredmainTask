//package com.vitrina.oracle;
//
//import oracle.jdbc.pool.OracleDataSource;
//
//import java.sql.Connection;
//import java.util.Locale;
//
///**
// * Created by alexandr on 07.09.15.
// * @author administrator
// * {@link http://forum.sources.ru/index.php?showtopic=201692&view=showall}
// * **************************************************
// * java.sql.SQLException: ORA-00604: error occurred at recursive SQL level 1 ORA-12705: Cannot access NLS data files or invalid environment specified
// *
//   {@link http://stackoverflow.com/questions/2747462/how-to-correctly-set-the-oracle-home-variable-on-ubuntu-9-x}
//   {@link http://www.unix.com/shell-programming-and-scripting/190687-solved-oraclsp2-0750-you-may-need-set-oracle_home-your-oracle-software-directory.html}
// * {@link http://techxploration.blogspot.com/2012/01/resolving-sp2-0750-you-may-need-to-set.html}
// * {@link http://docs.oracle.com/cd/B25329_01/doc/admin.102/b25610/toc.htm}
// * ********************************
// * > su oracle
// * >   oracle
// *
// * ORACLE_HOME=/u01/app/oracle/product/11.2.0
//   ORACLE_HOME=/home/oracle-xe/app/oracle/product/11.2.0
//   export ORACLE_HOME
//   export ORACLE_HOME=/u01/app/oracle/product/11.2.0
//   export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
//   ORACLE_SID=XE
//   export ORACLE_SID
//   NLS_LANG=`$ORACLE_HOME/bin/nls_lang.sh`
//   export NLS_LANG
//   PATH=$ORACLE_HOME/bin:$PATH
//   export PATH
//
//   /home/oracle-xe/app/oracle/product/11.2.0/xe/bin/oracle_env.sh
//   ./sqlplus system as sysdba
//
// * export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
// * /u01/app/oracle/product/11.2.0/xe/bin/sqlplus
// * SYSTEM
// * oracle
// *
// * {@link http://www.mandsconsulting.com/installing-oracle-database-11g-xe-and-unlocking-the-hr-sample-schema-a-tutorial-with-screenshots}
// *
// * SQL> ALTER USER HR IDENTIFIED BY hr ACCOUNT UNLOCK;
// *
// * User altered.
// *
// * SQL> quit
// *
// *
// * grant connect, resource to HR;
// */
//public class JDBCConnect2 {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Locale.setDefault(new Locale("es","ES"));
//
//            String        myURL = "jdbc:oracle:thin:@//10.13.71.34:1521/HR"; //String        myURL = "jdbc:oracle:thin:@hr";
//            OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
//            ds.setURL(myURL);
////            ds.setServerName("10.13.71.34");
//            Connection conn = ds.getConnection("HR", "hr");
//
////            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
////            String      sql = "SELECT * FROM HELP";
////            Statement  stat = conn.createStatement();
////            ResultSet    rs = stat.executeQuery(sql);
////
////            while (rs.next())
////                System.out.println(rs.getString("TOPIC") + "   " + rs.getString("SEQ") + "   " + rs.getString("INFO"));
//
//            conn.close();
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//    }
//
//    public static String      URL = "jdbc:oracle:thin:@//10.13.71.34:1521/XE";
//    public static String     USER = "SYSTEM";
//    public static String PASSWORD = "oracle";
//}
