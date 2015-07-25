package com.vitrina.dao;

import com.vitrina.dao.issue.Issue1Dao;
import com.vitrina.dao.issue.Issue2Dao;
import com.vitrina.dao.issue.Issue3Dao;
import com.vitrina.util.FactoryDriver;

/**
 * Created by alexandr on 24.07.15.
 */
public class FactoryDao {

    private static IssueDao issueDao;

    private FactoryDao(){}

    public static IssueDao getInstance(DAO type){
        switch (type){
            case POOL:
                issueDao = new Issue1Dao();                                 /* Issue1Dao */
                break;
            case PERSISTENCE:
                issueDao = new Issue2Dao(FactoryDriver.getEntityManager()); /* Issue2Dao */
                break;
            case HIBERNATE:
                issueDao = new Issue3Dao(FactoryDriver.getSession());       /* Issue3Dao */
                break;
        }
        return issueDao;
    }
}
