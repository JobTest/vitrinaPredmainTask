package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author dn200978lak
 */
public class DB {

    private Connection        connect           = null;
    private Statement         statement         = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet         resultSet         = null;
    private Properties        properties        = null;

    private String USER     = "root";
    private String PASSWORD = "1111";
    private String URL      = "jdbc:mysql://localhost:3306/issues";


    public void select() throws Exception {
        try {
            /* This will load the MySQL driver, each DB has its own driver */
            Class.forName("com.mysql.jdbc.Driver");
            properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "1111");

            /* настройки указывающие о необходимости конвертировать данные из Unicode в UTF-8, который используется в нашей таблице для хранения данных */
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8"); //properties.setProperty("characterEncoding","CP-1251"); //properties.setProperty("characterEncoding","windows-1251"); //properties.setProperty("characterEncoding","latin-1");
            connect = DriverManager.getConnection(URL, properties);

            /* Statements allow to issue SQL queries to the database */
            preparedStatement = connect.prepareStatement("SELECT * from issue");

            for (Issue issue : convert(preparedStatement.executeQuery()))
                System.out.println(issue);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private List<Issue> convert(ResultSet resultSet) throws SQLException {
        List<Issue> issues = new ArrayList<>();
        while (resultSet.next()) {
            Issue issue = new Issue();

            issue.setId( resultSet.getInt("id") );
            issue.setParentId( resultSet.getInt("parent_id") );
            issue.setProjectId( resultSet.getInt("project_id") );
            issue.setProjectName( resultSet.getString("project_name") );
            issue.setTrackerId( resultSet.getInt("tracker_id") );
            issue.setTrackerName( resultSet.getString("tracker_name") );
            issue.setStatusId( resultSet.getInt("status_id") );
            issue.setFixedVersionId( resultSet.getInt("fixed_version_id") );
            issue.setStatusName( resultSet.getString("status_name") );
            issue.setFixedVersionName( resultSet.getString("fixed_version_name") );
            issue.setSubject( resultSet.getString("subject") );
            issue.setStartDate( resultSet.getString("start_date") );
            issue.setDueDate( resultSet.getString("due_date") );

            issues.add(issue);
        }

        return issues;
    }

    public void insert(String sql) throws Exception {
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

    public void remove(int id) throws Exception {
        try {
            /* This will load the MySQL driver, each DB has its own driver */
            Class.forName("com.mysql.jdbc.Driver");
            /*  Setup the connection with the DB */
            connect = DriverManager.getConnection(URL + "?" + "user=" + USER + "&password=" + PASSWORD);

            /* Statements allow to issue SQL queries to the database */
            preparedStatement = connect.prepareStatement("DELETE FROM issues WHERE id="+id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private void close() {
        /* You need to close the resultSet */
        try {
            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (connect != null)
                connect.close();
        } catch (Exception e) {}
    }

}