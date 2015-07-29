package com.vitrina.dao;

import com.vitrina.dao.issue.IssueJDBCDao;
import com.vitrina.dao.issue.IssueJPADao;
import com.vitrina.dao.issue.IssueHibernateDao;
import com.vitrina.dao.issue.IssueSpringDao;
import com.vitrina.util.FactoryDriver;

/**
 * Created by alexandr on 24.07.15.
 */
public class FactoryDao {

    private static IssueDao issueDao;

    private FactoryDao(){}

    public static IssueDao getIssue(DAO type){
        switch (type){
            case JDBC:
                issueDao = new IssueJDBCDao();                                /* IssueJDBCDao */
                break;
            case JPA:
                issueDao = new IssueJPADao(FactoryDriver.getEntityManager()); /* IssueJPADao */
                break;
            case HIBERNATE:
                issueDao = new IssueHibernateDao(FactoryDriver.getSession()); /* IssueHibernateDao */
                break;
            case SPRING:
                issueDao = new IssueSpringDao(FactoryDriver.getDataSource()); /* IssueSpringDao */
                break;
        }
        return issueDao;
    }

    ////////////////////////////////////////////////////////
    public static IssueDao getIssueJDBCDao(){
        return new IssueJDBCDao();
    }
    public static IssueDao getIssueJPADao(){
        return new IssueJPADao(FactoryDriver.getEntityManager());
    }
    public static IssueDao getIssueHibernateDao(){
        return new IssueHibernateDao(FactoryDriver.getSession());
    }
    public static IssueDao getIssueSpringDao(){
        return new IssueSpringDao(FactoryDriver.getDataSource());
    }
    ////////////////////////////////////////////////////////
}
