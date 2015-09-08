package com.vitrina.dao.issue;

import org.hibernate.HibernateException;
import org.hibernate.internal.util.StringHelper;

import java.sql.*;
import java.util.Locale;

/**
 * @author Lazarchuk Aleksandr
 * @version miratex-master
 * @date 08/09/2015
 * **********************************
 * http://www.java2s.com/Code/Java/Database-SQL-JDBC/Testofloadingadriverandconnectingtoadatabase.htm
 * http://examples.javacodegeeks.com/core-java/sql/jdbc-databasemetadata-example/
 * http://stackoverflow.com/questions/1601203/jdbc-databasemetadata-getcolumns-returns-duplicate-columns
 * http://stackoverflow.com/questions/2780284/how-to-get-all-table-names-from-a-database
 * http://www.programcreek.com/java-api-examples/index.php?api=java.sql.DatabaseMetaData
 * http://stackoverflow.com/questions/12725041/check-sql-database-connection-in-java
 * http://stackoverflow.com/questions/7764671/java-jdbc-connection-status
 * http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html
 *
 */

public class DBUtility {
    private static final String[] TYPES = {"TABLE","VIEW"};
    public static void getTableMetadata(Connection jdbcConnection, String tableNamePattern, String schema, String catalog, boolean isQuoted) throws HibernateException {
        try {
            DatabaseMetaData meta = jdbcConnection.getMetaData();
            ResultSet rs = null;
            try {
                if ( (isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
                    rs = meta.getTables(catalog,schema,tableNamePattern,TYPES);
                } else if ( (isQuoted && meta.storesUpperCaseQuotedIdentifiers())
                        || (!isQuoted && meta.storesUpperCaseIdentifiers())) {
                    rs = meta.getTables(StringHelper.toUpperCase(catalog),StringHelper.toUpperCase(schema),StringHelper.toUpperCase(tableNamePattern),TYPES);
                } else if ( (isQuoted && meta.storesLowerCaseQuotedIdentifiers())
                        || (!isQuoted && meta.storesLowerCaseIdentifiers() )) {
                    rs = meta.getTables(StringHelper.toLowerCase(catalog),StringHelper.toLowerCase(schema),StringHelper.toLowerCase(tableNamePattern),TYPES);
                } else {
                    rs = meta.getTables(catalog,schema,tableNamePattern,TYPES);
                }

                while ( rs.next() )
                    System.out.println("table: " + rs.getString("TABLE_NAME"));
            }
            finally {
                if (rs!=null) rs.close();
            }
        } catch (SQLException sqlException) { sqlException.printStackTrace(); }

    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) { e.printStackTrace(); }

        try {
            Connection jdbcConnection = DriverManager.getConnection("jdbc:oracle:thin:@//10.13.71.34:1521/XE", "HR", "hr");
            getTableMetadata(jdbcConnection, "%", null, null, false); //getTableMetadata(jdbcConnection, "tbl%", null, null, false);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
