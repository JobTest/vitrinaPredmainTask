package com.vitrina.dao;

import com.vitrina.dao.issue.IssueJDBCDao;
import com.vitrina.dao.issue.IssueJPADao;
import com.vitrina.dao.issue.IssueHibernateDao;
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
                issueDao = new IssueJDBCDao();                                /* Issue1Dao */
                break;
            case PERSISTENCE:
                issueDao = new IssueJPADao(FactoryDriver.getEntityManager()); /* Issue2Dao */
                break;
            case HIBERNATE:
                issueDao = new IssueHibernateDao(FactoryDriver.getSession()); /* Issue3Dao */
                break;
        }
        return issueDao;
    }
}
