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

    public static IssueDao getIssue(DAO type, String dbConfig){
        switch (type){
            case JDBC:
                try {
                    issueDao = new IssueJDBCDao(dbConfig);                            /* IssueJDBCDao */
                } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
                break;
            case JPA:
                issueDao = new IssueJPADao(FactoryDriver.getEntityManager(dbConfig)); /* IssueJPADao */
                break;
            case HIBERNATE:
                issueDao = new IssueHibernateDao(FactoryDriver.getSession(dbConfig)); /* IssueHibernateDao */
                break;
        }
        return issueDao;
    }

    public static IssueDao getIssue(DAO type, DataSource dbConfig){
        switch (type){
            case SPRING:
                issueDao = new IssueSpringJDBCDao(FactoryDriver.getDataSource(dbConfig)); /* IssueSpringDao */
                break;
        }
        return issueDao;
    }


    public static IssueDao getIssueJDBCDao(String dbConfig){
        try {
            issueDao = new IssueJDBCDao(dbConfig);
        } catch (IOException e) { System.err.println("[IOException] " + e.getMessage()); }
        return issueDao;
    }
    public static IssueDao getIssueJPADao(String dbConfig){
        return new IssueJPADao(FactoryDriver.getEntityManager(dbConfig));
    }
    public static IssueDao getIssueHibernateDao(String dbConfig){
        return new IssueHibernateDao(FactoryDriver.getSession(dbConfig));
    }
    public static IssueDao getIssueSpringDao(DataSource dbConfig){
        return new IssueSpringJDBCDao(FactoryDriver.getDataSource(dbConfig));
    }

    private static IssueDao issueDao;
}
