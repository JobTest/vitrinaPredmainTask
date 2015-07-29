package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueJDBC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Саша on 30.07.2015.
 ** {@link http://javatalks.ru/topics/25965}
 * {@link http://spring-projects.ru/guides/relational-data-access/}
 * ****************************************************************
 * Работа со spring-jdbc
 */
public class IssueSpringDao implements IssueDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    private final String SQL_GET_ALL = "SELECT * FROM issue";
    private final String     SQL_GET = "SELECT * FROM issue WHERE id=?";
    private final String     SQL_ADD = "INSERT INTO issue (id,parent_id,project_id,project_name,tracker_id,tracker_name,fixed_version_id,fixed_version_name,status_id,status_name,subject,start_date,due_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String  SQL_UPDATE = "UPDATE issue SET parent_id=?,project_id=?,project_name=?,tracker_id=?,tracker_name=?,fixed_version_id=?,fixed_version_name=?,status_id=?,status_name=?,subject=?,start_date=?,due_date=? WHERE id=?";
    private final String  SQL_DELETE = "DELETE FROM issues WHERE id=?";

    public IssueSpringDao(){}
    public IssueSpringDao(DataSource dataSource) {
        jdbcTemplate  = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Issue> getAll(List<Issue> select) throws Exception {
        return jdbcTemplate.query(SQL_GET_ALL, rowMapper);
    }
//    public Issue get(Integer id) {
//        return jdbcTemplate.queryForObject(SQL_GET, rowMapper, id);
//    }

    @Override
    public void add(List<Issue> issues) throws Exception {
        for (Issue issue:issues)
            jdbcTemplate.update(SQL_ADD, getPreparedStatementSetter(issue));
    }

    @Override
    public void delete(int id) throws Exception {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Issue issue) throws Exception {
        jdbcTemplate.update(SQL_UPDATE, getPreparedStatementSetter(issue));
    }

//    public List<Issue> findByCriteria(IssueSearchCriteria criteria) {
//        if (criteria.isEmpty()) {
//            return getAll();
//        }
//        String sql = "SELECT * FROM book WHERE true";
//        if (criteria.getComment() != null) {
//            sql += " AND comment=:comment";
//        }
//        if (criteria.getTitle() != null) {
//            sql += " AND title=:title";
//        }
//        if (criteria.getMaxDateRelease() != null) {
//            sql += " AND date_release<:maxDateRelease";
//        }
//        if (criteria.getMinDateRelease() != null) {
//            sql += " AND date_release>:minDateRelease";
//        }
//        if (criteria.getAuthorId() != null) {
//            sql += " AND author_id=:authorId";
//        }
//        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
//        return namedTemplate.query(sql, namedParameters, rowMapper);
//    }


    private RowMapper<Issue> rowMapper = new RowMapper<Issue>() {
        @Override
        public Issue mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Issue issue = new IssueJDBC(resultSet.getInt("id"),resultSet.getInt("parent_id"),resultSet.getInt("project_id"),resultSet.getString("project_name"),resultSet.getInt("tracker_id"),resultSet.getString("tracker_name"),resultSet.getInt("status_id"),resultSet.getString("status_name"),resultSet.getInt("fixed_version_id"),resultSet.getString("fixed_version_name"),resultSet.getString("subject"),resultSet.getString("start_date"),resultSet.getString("due_date"));
            return issue;
        }
    };

    private PreparedStatementSetter getPreparedStatementSetter(final Issue issue) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setInt(++i, issue.getId());
                ps.setInt(++i, issue.getParentId());
                ps.setInt(++i, issue.getProjectId());
                ps.setString(++i, issue.getProjectName());
                ps.setInt(++i, issue.getTrackerId());
                ps.setString(++i, issue.getTrackerName());
                ps.setInt(++i, issue.getFixedVersionId());
                ps.setString(++i, issue.getFixedVersionName());
                ps.setInt(++i, issue.getStatusId());
                ps.setString(++i, issue.getStatusName());
                ps.setString(++i, issue.getSubject());
                ps.setString(++i, issue.getStartDate());
                ps.setString(++i, issue.getDueDate());
            }
        };
    }
}
