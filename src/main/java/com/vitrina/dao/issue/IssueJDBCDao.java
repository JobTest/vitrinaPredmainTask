package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueJDBC;
import com.vitrina.util.FactoryDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexandr on 24.07.15.
 */
public class IssueJDBCDao implements IssueDao {

    private PreparedStatement preparedStatement = null;
    private ResultSet                 resultSet = null;
    public Connection                connection = null;
    private final String            SQL_GET_ALL = "SELECT * FROM issue";
    private final String                SQL_ADD = "INSERT INTO issue (id,parent_id,project_id,project_name,tracker_id,tracker_name,fixed_version_id,fixed_version_name,status_id,status_name,subject,start_date,due_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String             SQL_UPDATE = "UPDATE issue SET parent_id=?,project_id=?,project_name=?,tracker_id=?,tracker_name=?,fixed_version_id=?,fixed_version_name=?,status_id=?,status_name=?,subject=?,start_date=?,due_date=? WHERE id=?";
    private final String             SQL_DELETE = "DELETE FROM issues WHERE id=?";

    public IssueJDBCDao(){}
    public IssueJDBCDao(String configFile) throws IOException {
        connection = FactoryDriver.getConnection(configFile);
    }

    @Override
    public List<Issue> getAll(List<Issue> select) throws Exception {
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL); //preparedStatement = FactoryDriver.getConnection().prepareStatement(SQL_GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                select.add( new IssueJDBC(resultSet.getInt("id"),resultSet.getInt("parent_id"),resultSet.getInt("project_id"),resultSet.getString("project_name"),resultSet.getInt("tracker_id"),resultSet.getString("tracker_name"),resultSet.getInt("status_id"),resultSet.getString("status_name"),resultSet.getInt("fixed_version_id"),resultSet.getString("fixed_version_name"),resultSet.getString("subject"),resultSet.getString("start_date"),resultSet.getString("due_date")) );
        } catch(SQLException e){ System.err.print("[getAll] " + e.getMessage());
        } finally { if (preparedStatement != null) preparedStatement.close(); if (resultSet != null) resultSet.close(); }

        return select;
    }

    @Override
    public void add(List<Issue> insert) throws Exception {
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD); //preparedStatement = FactoryDriver.getConnection().prepareStatement(SQL_ADD);
            connection.setAutoCommit(false); //FactoryDriver.getConnection().setAutoCommit(false);
            for (Issue issue:insert) {
                int i=0;
                preparedStatement.setInt(++i, issue.getId());
                preparedStatement.setInt(++i, issue.getParentId());
                preparedStatement.setInt(++i, issue.getProjectId());
                preparedStatement.setString(++i, issue.getProjectName());
                preparedStatement.setInt(++i, issue.getTrackerId());
                preparedStatement.setString(++i, issue.getTrackerName());
                preparedStatement.setInt(++i, issue.getFixedVersionId());
                preparedStatement.setString(++i, issue.getFixedVersionName());
                preparedStatement.setInt(++i, issue.getStatusId());
                preparedStatement.setString(++i, issue.getStatusName());
                preparedStatement.setString(++i, issue.getSubject());
                preparedStatement.setString(++i, issue.getStartDate());
                preparedStatement.setString(++i, issue.getDueDate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit(); //FactoryDriver.getConnection().commit();
        } catch (SQLException e) {
            System.err.println("[add] " + e.getMessage());
            try {
                /* When this code is executed, update statement is hits error, and make both insert and update statements rollback together */
                connection.rollback(); //FactoryDriver.getConnection().rollback();
            } catch (SQLException e1) { System.err.println("[rollback] " + e.getMessage()); }
        } finally { if (preparedStatement != null) preparedStatement.close(); }
    }

    @Override
    public void update(Issue issue) throws Exception {
        try{
//            preparedStatement = FactoryDriver.getConnection().prepareStatement("UPDATE issue SET parent_id='" + issue.getParentId() + "',project_id='" + issue.getProjectId() + "',project_name='" + issue.getProjectName() + "',tracker_id='" + issue.getTrackerId() + "',tracker_name='" + issue.getTrackerName() + "',fixed_version_id='" + issue.getFixedVersionId() + "',fixed_version_name='" + issue.getFixedVersionName() + "',status_id='" + issue.getStatusId() + "',status_name='" + issue.getStatusName() + "',subject='" + issue.getSubject() + "',start_date='" + issue.getStartDate() + "',due_date='" + issue.getDueDate() + "' WHERE id='" + issue.getId() + "'");
            int i=0;
            preparedStatement = connection.prepareStatement(SQL_UPDATE); //preparedStatement = FactoryDriver.getConnection().prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(++i, issue.getParentId());
            preparedStatement.setInt(++i, issue.getProjectId());
            preparedStatement.setString(++i, issue.getProjectName());
            preparedStatement.setInt(++i, issue.getTrackerId());
            preparedStatement.setString(++i, issue.getTrackerName());
            preparedStatement.setInt(++i, issue.getFixedVersionId());
            preparedStatement.setString(++i, issue.getFixedVersionName());
            preparedStatement.setInt(++i, issue.getStatusId());
            preparedStatement.setString(++i, issue.getStatusName());
            preparedStatement.setString(++i, issue.getSubject());
            preparedStatement.setString(++i, issue.getStartDate());
            preparedStatement.setString(++i, issue.getDueDate());
            preparedStatement.setInt(++i, issue.getId());
            connection.commit(); //FactoryDriver.getConnection().commit();
        } catch (SQLException e) {
            System.err.println("[update] " + e.getMessage());
            try {
                connection.rollback(); //FactoryDriver.getConnection().rollback();
            } catch (SQLException e1) { System.err.println("[rollback] " + e.getMessage()); }
        }
    }
    public void update(List<Issue> issues) throws Exception {
        try {
            for (Issue issue:issues){
//                preparedStatement = FactoryDriver.getConnection().prepareStatement("UPDATE issue SET parent_id='" + issue.getParentId() + "',project_id='" + issue.getProjectId() + "',project_name='" + issue.getProjectName() + "',tracker_id='" + issue.getTrackerId() + "',tracker_name='" + issue.getTrackerName() + "',fixed_version_id='" + issue.getFixedVersionId() + "',fixed_version_name='" + issue.getFixedVersionName() + "',status_id='" + issue.getStatusId() + "',status_name='" + issue.getStatusName() + "',subject='" + issue.getSubject() + "',start_date='" + issue.getStartDate() + "',due_date='" + issue.getDueDate() + "' WHERE id='" + issue.getId() + "'");
                int i=0;
                preparedStatement = connection.prepareStatement(SQL_UPDATE); //preparedStatement = FactoryDriver.getConnection().prepareStatement(SQL_UPDATE);
                preparedStatement.setInt(++i, issue.getParentId());
                preparedStatement.setInt(++i, issue.getProjectId());
                preparedStatement.setString(++i, issue.getProjectName());
                preparedStatement.setInt(++i, issue.getTrackerId());
                preparedStatement.setString(++i, issue.getTrackerName());
                preparedStatement.setInt(++i, issue.getFixedVersionId());
                preparedStatement.setString(++i, issue.getFixedVersionName());
                preparedStatement.setInt(++i, issue.getStatusId());
                preparedStatement.setString(++i, issue.getStatusName());
                preparedStatement.setString(++i, issue.getSubject());
                preparedStatement.setString(++i, issue.getStartDate());
                preparedStatement.setString(++i, issue.getDueDate());
                preparedStatement.setInt(++i, issue.getId());
                preparedStatement.executeUpdate();
            }
            connection.commit(); //FactoryDriver.getConnection().commit();
        } catch (SQLException e) {
            System.err.println("[update] " + e.getMessage());
            try {
                connection.rollback(); //FactoryDriver.getConnection().rollback();
            } catch (SQLException e1) { System.err.println("[rollback] " + e.getMessage()); }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            int i=0;
            preparedStatement = connection.prepareStatement(SQL_DELETE); //preparedStatement = FactoryDriver.getConnection().prepareStatement(SQL_DELETE);
            preparedStatement.setInt(++i, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[delete] " + e.getMessage());
            try {
                /* When this code is executed, update statement is hits error, and make both insert and update statements rollback together */
                connection.rollback(); //FactoryDriver.getConnection().rollback();
            } catch (SQLException e1) { System.err.println("[rollback]" + e.getMessage()); }
        } finally { if (preparedStatement != null) preparedStatement.close(); }
    }
}