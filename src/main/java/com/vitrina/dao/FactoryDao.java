package com.vitrina.dao;

import com.vitrina.dao.issue.IssueHibernateDao;
import com.vitrina.dao.issue.IssueJDBCDao;
import com.vitrina.dao.issue.IssueJPADao;
import com.vitrina.dao.issue.IssueSpringJDBCDao;
import com.vitrina.util.FactoryDriver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by alexandr on 24.07.15.
 */
public class FactoryDao {

    private FactoryDao(){}

    public static IssueDao getIssue(DAO type, String config){
        switch (type){
            case JDBC:
                try {
                    issueDao = new IssueJDBCDao(config);                            /* IssueJDBCDao */
                } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
                break;
            case JPA:
                issueDao = new IssueJPADao(FactoryDriver.getEntityManager(config)); /* IssueJPADao */
                break;
            case HIBERNATE:
                issueDao = new IssueHibernateDao(FactoryDriver.getSession(config)); /* IssueHibernateDao */
                break;
        }
        return issueDao;
    }

    public static IssueDao getIssue(DAO type, DataSource config){
        switch (type){
            case SPRING:
                issueDao = new IssueSpringJDBCDao(FactoryDriver.getDataSource(config)); /* IssueSpringDao */
                break;
        }
        return issueDao;
    }


    public static IssueDao getIssueJDBCDao(String config){
        try {
            issueDao = new IssueJDBCDao(config);
            return new IssueJDBCDao(config);
        } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
        return issueDao;
    }
    public static IssueDao getIssueJPADao(String config){
        return new IssueJPADao(FactoryDriver.getEntityManager(config));
    }
    public static IssueDao getIssueHibernateDao(String config){
        return new IssueHibernateDao(FactoryDriver.getSession(config));
    }
    public static IssueDao getIssueSpringDao(DataSource config){
        return new IssueSpringJDBCDao(FactoryDriver.getDataSource(config));
    }

    private static IssueDao issueDao;
}
