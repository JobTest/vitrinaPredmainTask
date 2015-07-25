package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

/**
* Created by alexandr on 24.07.15.
*/
public class Issue3Dao implements IssueDao {

    private Session session;

    public Issue3Dao(){}
    public Issue3Dao(Session session){
        this.session = session;
    }

    public void add(Issue issue){
        try {
            session.getTransaction().begin();
            session.save(issue);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }
    public void add(List<Issue> issues){
        try {
            session.getTransaction().begin();
            for (Issue issue:issues)
                session.save(issue);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Issue> getAll(List<Issue> select){
        return select = session.createCriteria(Issue.class).list();
    }
    public List<Issue> getAll(int limit) {
        return session.createCriteria(Issue.class).setMaxResults(limit).list();
    }
    public List<Issue> getAll(String sort){
        return session.createCriteria(Issue.class).addOrder(Order.asc(sort)).list();
    }
    public List<Issue> getAll(int limit, String sort) {
        return session.createCriteria(Issue.class).setMaxResults(limit).addOrder(Order.asc(sort)).list();
    }

    public Issue find(int id){
        return (Issue)session.get(Issue.class, id);
    }
    public List<Issue> find(int minId, int maxId){
        return session.createCriteria(Issue.class).add(Expression.between("id", minId, maxId)).list();
    }
    public List<Issue> findByTracker(String like){
        return session.createCriteria(Issue.class).add(Expression.like("TrackerName", like + "%")).list();
    }

    public void update(Issue issue){
        try{
            session.getTransaction().begin();
            session.update(issue);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e){
            System.err.println(e.getMessage());
        }
    }

    public void delete(Issue issue){
        try{
            session.getTransaction().begin();
            session.delete(issue);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){ System.out.println(e.getMessage()); }
    }
    public void delete(int id){
        try{
            session.getTransaction().begin();
            session.delete( find(id) );
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){ System.out.println(e.getMessage()); }
    }
}