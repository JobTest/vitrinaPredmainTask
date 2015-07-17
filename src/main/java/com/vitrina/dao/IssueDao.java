package com.vitrina.dao;

import com.vitrina.domain.Issue;
import com.vitrina.utils.DB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author dn200978lak
 */
public class IssueDao {

    private Connection        connect           = null;
    private Statement         statement         = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet         resultSet         = null;
    private Properties        properties        = null;

    private String USER     = "root";
    private String PASSWORD = "1111";
    private String URL      = "jdbc:mysql://localhost:3306/vitrina";

    public List<Issue> select(String sql) throws Exception {
        List<Issue> select = new LinkedList<>();
        try {
            preparedStatement = DB.getInstance().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
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
                select.add(issue);
            }
        } catch(SQLException e){ System.err.print("-SQLException-");
        } finally { close(); }

        return select;
    }



    public void insert(String sql) throws Exception {
        try {
            preparedStatement = DB.getInstance().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            close();
        }
//        //Example Using UpdateMany
//        Customer firstCustomer = new Customer("Customer3","Costa Rica","Main Street","Arenal", "La Fortuna","90291","506-375-0273",null);
//        Customer secondCustomer = new Customer("Customer4","Puerto Rico","Church Street", "Puerto Nuevo","San Juan","38364","293-484-8244",null);
//
//        List<Customer> customers = new ArrayList<>();
//        customers.add (firstCustomer);
//        customers.add (secondCustomer);
//
//        sqlInsert = "INSERT INTO PDQ_SC.customer (Name,Country,Street,City,Province,Zip,Phone) VALUES (:name,:country,:street,:city,:province,:zip,:phone)";
//        int[] countUpdate = data.updateMany(sql, customers);
//        if (countUpdate != null)
//            System.out.println ("Rows Inserted:" + countUpdate.length);
    }

    public void remove(int id) throws Exception {
        try {
            /* Statements allow to issue SQL queries to the database */
            preparedStatement = DB.getInstance().prepareStatement("DELETE FROM issues WHERE id=" + id);
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