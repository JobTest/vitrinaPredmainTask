package com.vitrina.dao;

import com.vitrina.dao.issue.IssueHibernateDao;
import com.vitrina.dao.issue.IssueJDBCDao;
import com.vitrina.dao.issue.IssueJPADao;
import com.vitrina.dao.issue.IssueSpringDao;
import com.vitrina.util.FactoryDriver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by alexandr on 24.07.15.
 */
public class FactoryDao {

    private static IssueDao issueDao;

    private FactoryDao(){}

    public static IssueDao getIssue(DAO type, String configFile){
        switch (type){
            case JDBC:
                try {
                    issueDao = new IssueJDBCDao(configFile);                            /* IssueJDBCDao */
                } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
                break;
            case JPA:
                issueDao = new IssueJPADao(FactoryDriver.getEntityManager(configFile)); /* IssueJPADao */
                break;
            case HIBERNATE:
                issueDao = new IssueHibernateDao(FactoryDriver.getSession(configFile)); /* IssueHibernateDao */
                break;
//            case SPRING:
//                issueDao = new IssueSpringDao(FactoryDriver.getDataSource(configFile)); /* IssueSpringDao */
//                break;
        }
        return issueDao;
    }
    public static IssueDao getIssue(DAO type, DataSource dataSource){
        switch (type){
            case SPRING:
                issueDao = new IssueSpringDao(FactoryDriver.getDataSource(dataSource)); /* IssueSpringDao */
                break;
        }
        return issueDao;
    }

    ////////////////////////////////////////////////////////
    public static IssueDao getIssueJDBCDao(String configFile){
        try {
            issueDao = new IssueJDBCDao(configFile);
            return new IssueJDBCDao(configFile);
        } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
        return issueDao;
    }
    public static IssueDao getIssueJPADao(String configFile){
        return new IssueJPADao(FactoryDriver.getEntityManager(configFile));
    }
    public static IssueDao getIssueHibernateDao(String configFile){
        return new IssueHibernateDao(FactoryDriver.getSession(configFile));
    }
    public static IssueDao getIssueSpringDao(DataSource dataSource){ //public static IssueDao getIssueSpringDao(String configFile){
        return new IssueSpringDao(FactoryDriver.getDataSource(dataSource)); //return new IssueSpringDao(FactoryDriver.getDataSource(configFile));
    }
    ////////////////////////////////////////////////////////
}
