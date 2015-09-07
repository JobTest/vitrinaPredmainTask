package com.vitrina.oracle.windows;

import java.sql.*;
import java.util.Locale;

/**
 * Created by alexandr on 07.09.15.
 * @author alexandr
 * {@link https://community.oracle.com/thread/430906}
 * {@link http://www.wikiguga.com/topic/08dbc5d44687e108ecd8e48455c4ca73}
 * {@link http://www.sql.ru/forum/864343/ibm-rad-proekt-jpa-i-konnekt-k-oracle-10g-kak}
   {@link http://forum.sources.ru/index.php?showtopic=201692&view=showall}
 * ***********************************************************************
 * java.sql.SQLException: ORA-00604: error occurred at recursive SQL level 1 ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * ORA-00604: error occurred at recursive SQL level 1
 * ORA-12705: Cannot access NLS data files or invalid environment specified
 *
 * VM options: -Duser.language=en -Duser.region=us
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
 * 1. Заходим: юзером 'SYSTEM' в 'Oracle SQL Developer'
 * 2. Hазворачиваем/Выбираем: 'Other Users' >> 'HR' >> [Edit User]
 * 3. Устанавливаем/Снимаем: 'New Password', 'Confirm Password', 'Acount is looked'
 *
 * SQL> CONNECT HR/hr
 * Connected.
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
 * > cd C:\oraclexe\app\oracle\product\11.2.0\server\apex
 * > sqlplus
 *  SYSTEM
 *  oracle2
 * SQL> @apxchpwd.sql
Enter a value below for the password for the Application Express ADMIN user.
 * admin

Enter a password for the ADMIN user              []

Session altered.

...changing password for ADMIN

PL/SQL procedure successfully completed.


Commit complete.
 *
 * URL: http://127.0.0.1:8080/apex/apex_admin
 * ADMIN / admin
 *
 * ADMIN / !A1d9m7i8n
 *
 * >> [Reset User Password]
 *     User     Full Name           Workspace       Default Schema  Created     Last Updated    Password
 * >> 'ALEX 	                    HR              HR              ...         ...             Reset'
 * >> [Edit]
 *
 * URL(user): http://127.0.0.1:8080/apex
 * HR / ALEX / alex
 * HR / ALEX / alex2
 */
public class JDBCConnect2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.US); //Locale.setDefault(new Locale("es","ES"));

            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String      sql = "SELECT * FROM EMPLOYEES";
            Statement  stat = conn.createStatement();
            ResultSet    rs = stat.executeQuery(sql);

            while (rs.next())
                System.out.println(rs.getString("FIRST_NAME") + "   " + rs.getString("LAST_NAME") + "   " + rs.getString("EMAIL"));

            conn.close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }

    public static String      URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
    public static String     USER = "HR";
    public static String PASSWORD = "hr";
}
