package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.IssueJDBC;
import com.vitrina.domain.Issue;
import com.vitrina.util.FactoryDriver;

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

    @Override
    public List<Issue> getAll(List<Issue> select) throws Exception {
        try {
            preparedStatement = FactoryDriver.getConnection().prepareStatement("SELECT * FROM issue");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                select.add( new IssueJDBC(resultSet.getInt("id"),resultSet.getInt("parent_id"),resultSet.getInt("project_id"),resultSet.getString("project_name"),resultSet.getInt("tracker_id"),resultSet.getString("tracker_name"),resultSet.getInt("status_id"),resultSet.getString("status_name"),resultSet.getInt("fixed_version_id"),resultSet.getString("fixed_version_name"),resultSet.getString("subject"),resultSet.getString("start_date"),resultSet.getString("due_date")) );
        } catch(SQLException e){ System.err.print("-SQLException-");
        } finally { if (preparedStatement != null) preparedStatement.close(); if (resultSet != null) resultSet.close(); }

        return select;
    }

    @Override
    public void add(List<Issue> insert) throws Exception {
        try {
            preparedStatement = FactoryDriver.getConnection().prepareStatement("INSERT INTO issue (id,parent_id,project_id,project_name,tracker_id,tracker_name,fixed_version_id,fixed_version_name,status_id,status_name,subject,start_date,due_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            FactoryDriver.getConnection().setAutoCommit(false);
            for (Issue issue:insert) {
                preparedStatement.setInt(1, issue.getId());
                preparedStatement.setInt(2, issue.getParentId());
                preparedStatement.setInt(3, issue.getProjectId());
                preparedStatement.setString(4, issue.getProjectName());
                preparedStatement.setInt(5, issue.getTrackerId());
                preparedStatement.setString(6, issue.getTrackerName());
                preparedStatement.setInt(7, issue.getFixedVersionId());
                preparedStatement.setString(8, issue.getFixedVersionName());
                preparedStatement.setInt(9, issue.getStatusId());
                preparedStatement.setString(10, issue.getStatusName());
                preparedStatement.setString(11, issue.getSubject());
                preparedStatement.setString(12, issue.getStartDate());
                preparedStatement.setString(13, issue.getDueDate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            FactoryDriver.getConnection().commit();
        } catch (SQLException e) { System.err.println(e.getMessage());
        } finally { if (preparedStatement != null) preparedStatement.close(); }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            preparedStatement = FactoryDriver.getConnection().prepareStatement("DELETE FROM issues WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) { System.err.println(e.getMessage());
        } finally { if (preparedStatement != null) preparedStatement.close(); }
    }
}