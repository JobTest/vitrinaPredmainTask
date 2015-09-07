package com.vitrina.oracle.linux;

import java.sql.*;
import java.util.Locale;

/**
 * Created by alexandr on 07.09.15.
 * @author alexandr
 * {@link https://community.oracle.com/thread/430906}
   {@link http://forum.sources.ru/index.php?showtopic=201692&view=showall}
 * **************************************************
 * java.sql.SQLException: ORA-00604: error occurred at recursive SQL level 1 ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * ORA-00604: error occurred at recursive SQL level 1
 * ORA-12705: Cannot access NLS data files or invalid environment specified
 *
   {@link http://newsoalife.blogspot.com/2012/03/lost-oracle-sys-and-system-password.html}
   {@link http://how2-sapbasis.blogspot.com/2012/06/password-reset-for-oracle-user-system.html}
 * {@link http://techxploration.blogspot.com/2012/01/resolving-sp2-0750-you-may-need-to-set.html}
 * {@link http://docs.oracle.com/cd/B25329_01/doc/admin.102/b25610/toc.htm}
 * ********************************
 * > ssh jenkins@10.13.71.34
 * >   jenkins34
 * >
 * > su oracle
 * >   oracle
 *
 * > export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
 * > /u01/app/oracle/product/11.2.0/xe/bin/sqlplus
 *  SYSTEM
 *  oracle
 *
 * {@link http://www.mandsconsulting.com/installing-oracle-database-11g-xe-and-unlocking-the-hr-sample-schema-a-tutorial-with-screenshots}
 * {@link https://blogs.oracle.com/rajeshthekkadath/entry/resolving_oracle_error_ora_28000}
 * {@link http://docs.oracle.com/cd/E11882_01/server.112/e10831/installation.htm#COMSC00007}
 * ********************************
 * SQL> ALTER USER HR IDENTIFIED BY hr ACCOUNT UNLOCK;
 *
 * User altered.
 *
 * SQL> grant connect, resource to HR;
 *
 * Grant succeeded.
 *
 * SQL> quit
 *
 * > /u01/app/oracle/product/11.2.0/xe/bin/sqlplus
 *  HR
 *  hr
 *
 * SQL> SELECT table_name FROM user_tables;
 *
 * {@link http://docs.oracle.com/cd/B25329_01/doc/admin.102/b25610/toc.htm}
   {@link http://docs.oracle.com/cd/B13789_01/server.101/b10742/em_manage.htm}
 * ********************************
 * URL(обучение): http://10.13.71.34:8082/apex/f?p=4600:6:143374024929020:::::
 *
 * {@link http://blog.mclaughlinsoftware.com/2011/09/14/reset-11g-xe-apex-password/}
   {@link http://surachartopun.com/2013/05/the-account-is-locked-on-apex.html}
   {@link https://oracle2expert.wordpress.com/2013/09/11/apex-4-2-admin-account-locked/}
 * {@link http://blog.mclaughlinsoftware.com/tag/reset-apex-password/}
 ** {@link http://www.wikiguga.com/topic/76a0ef06c1faccb107c4d231e193948e}
 * ********************************
 * > ssh jenkins@10.13.71.34
 * >   jenkins34
 * >
 * > su oracle
 * >   oracle
 * >
 * > cd /home/oracle-xe/app/oracle/product/11.2.0/xe/apex
 * > /u01/app/oracle/product/11.2.0/xe/bin/sqlplus
 *  SYSTEM
 *  oracle
 * SQL> @apxchpwd.sql
   Enter a value below for the password for the Application Express ADMIN user.
 * admin

   Enter a password for the ADMIN user              []

   Session altered.

   ...changing password for ADMIN

   PL/SQL procedure successfully completed.


   Commit complete.
 *
 * URL: http://10.13.71.34:8082/apex/apex_admin
 * ADMIN / admin
 *
 * ADMIN / !DN200978lak
 *
 * >> [Reset User Password]
 *     User     Full Name           Workspace       Default Schema  Created     Last Updated    Password
 * >> 'ALEX2	Alexandr Lazarchuk  MY_WORKSPACE    ALEX            ...         ...             Reset'
 * >> [Edit]
 *
 * URL(user): http://10.13.71.34:8082/apex
 * MY_WORKSPACE / ALEX2 / alex2
 * MY_WORKSPACE / ALEX2 / !Sasha1978
 */
public class JDBCConnect2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(new Locale("es","ES"));

            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String      sql = "SELECT * FROM EMPLOYEES";
            Statement  stat = conn.createStatement();
            ResultSet    rs = stat.executeQuery(sql);

            while (rs.next())
                System.out.println(rs.getString("FIRST_NAME") + "   " + rs.getString("LAST_NAME") + "   " + rs.getString("EMAIL"));

            conn.close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }

    public static String      URL = "jdbc:oracle:thin:@//10.13.71.34:1521/XE";
    public static String     USER = "HR";
    public static String PASSWORD = "hr";
}
