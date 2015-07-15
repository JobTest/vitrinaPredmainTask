package com;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author dn200978lak
 */
public class VitrinaDbUpdateSosTask {

    private Connection        connect           = null;
    private Statement         statement         = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet         resultSet         = null;
    private Properties        properties        = null;

    private String USER     = "root";
    private String PASSWORD = "1111";
    private String URL      = "jdbc:mysql://localhost:3306/issues";

    /**
     * Result set get the result of the SQL query
     */
    public void readDataBase() throws Exception {
        try {
            /**
             * This will load the MySQL driver, each DB has its own driver
             */
            Class.forName("com.mysql.jdbc.Driver");
//            connect = DriverManager.getConnection(URL + "?" + "user=" + USER + "&password=" + PASSWORD);
            properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "1111");

            /**
             * настройки указывающие о необходимости конвертировать данные из Unicode в UTF-8, который используется в нашей таблице для хранения данных
             */
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
//            properties.setProperty("characterEncoding","CP-1251");
//            properties.setProperty("characterEncoding","windows-1251");
//            properties.setProperty("characterEncoding","latin-1");
            connect = DriverManager.getConnection(URL, properties);
            /**
             * Statements allow to issue SQL queries to the database
             */
            statement = connect.createStatement();

            /**
             * "myuser, webpage, datum, summary, COMMENTS from COMMENTS");
             * Parameters start with 1
             */
//            preparedStatement.setString(1, "Test");
//            preparedStatement.setString(2, "TestEmail");
//            preparedStatement.setString(3, "TestWebpage");
//            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//            preparedStatement.setString(5, "TestSummary");
//            preparedStatement.setString(6, "TestComment");
//            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("SELECT * from issue");
            resultSet         = preparedStatement.executeQuery();
            readResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    /**
     * PreparedStatements can use variables and are more efficient
     */
    public void writeDataBase(String sql) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "1111");
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            connect = DriverManager.getConnection(URL, properties);

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close();
        }
    }

    /**
     * Remove again the insert comment
     */
    public void removeDataBase(int id) throws Exception {
        try {
            /**
             * This will load the MySQL driver, each DB has its own driver
             */
            Class.forName("com.mysql.jdbc.Driver");
            /**
             *  Setup the connection with the DB
             */
            connect = DriverManager.getConnection(URL + "?" + "user=" + USER + "&password=" + PASSWORD);
            /**
             * Statements allow to issue SQL queries to the database
             */
            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("DELETE FROM issues WHERE id="+id);
//            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    /**
     * ResultSet is initially before the first data set
     * @param resultSet
     * @throws SQLException
     */
    private void readResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getString("id"));
            System.out.println("PARENT-ID: " + resultSet.getString("parent_id"));
            System.out.println("PROJECT-ID: " + resultSet.getString("project_id"));
            System.out.println("PROJECT-NAME: " + resultSet.getString("project_name"));
            System.out.println("TRACKER-ID: " + resultSet.getString("tracker_id"));
            System.out.println("TRACKER-NAME: " + resultSet.getString("tracker_name"));
            System.out.println("FIXED-VERSION-ID: " + resultSet.getString("fixed_version_id"));
            System.out.println("FIXED-VERSION-NAME: " + resultSet.getString("fixed_version_name"));
            System.out.println("STATUS-ID: " + resultSet.getString("status_id"));
            System.out.println("STATUS-NAME: " + resultSet.getString("status_name"));
            System.out.println("SUBJECT: " + resultSet.getString("subject"));
            System.out.println("START-DATE: " + resultSet.getString("start_date"));
            System.out.println("DUE-DATE: " + resultSet.getString("due_date"));
            System.out.println();
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {}
    }

}