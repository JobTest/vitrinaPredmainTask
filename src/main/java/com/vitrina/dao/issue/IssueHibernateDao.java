package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueHibernate;
import com.vitrina.util.FactoryDriver;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
* Created by alexandr on 24.07.15.
 * {@link http://apmblog.dynatrace.com/2009/03/24/understanding-caching-in-hibernate-part-three-the-second-level-cache/}
*/
public class IssueHibernateDao implements IssueDao {

    private Session session;

    public IssueHibernateDao(){}
    public IssueHibernateDao(Session session){
        this.session = session;
    }

    @Override
    public void add(List<Issue> issues){
        List<IssueHibernate> issuesHibernate = new LinkedList<>();
        for (Issue issue:issues)
            issuesHibernate.add( new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate()) );
        try {
            Transaction transaction = session.beginTransaction();
            for (IssueHibernate issue:issuesHibernate)
                session.save(issue);
            transaction.commit();
        } catch(ExceptionInInitializerError e) {
            System.err.println("[add] " + e.getMessage());
        }
    }
    public void add(Issue issue){
        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        try {
            Transaction transaction = session.beginTransaction();
            session.save(issuesHibernate);
            transaction.commit();
        } catch (ExceptionInInitializerError e) {
            System.err.println("[add] " + e.getMessage());
        }
    }

    @Override
    public List<Issue> getAll(List<Issue> issues){
//        return issues = session.createCriteria(IssueHibernate.class).list();

        try {
            session = FactoryDriver.getSession("hibernate_mysql.cft.xml");
            issues = session.createCriteria(IssueHibernate.class).list();
        } catch (Exception e){
            System.err.println("[getAll] " + e.getMessage());
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return issues;
    }
    public List<Issue> getAll(int limit) {
        return session.createCriteria(IssueHibernate.class).setMaxResults(limit).list();
    }
    public List<Issue> getAll(String sort){
        return session.createCriteria(IssueHibernate.class).addOrder(Order.asc(sort)).list();
    }
    public List<Issue> getAll(int limit, String sort) {
        return session.createCriteria(IssueHibernate.class).setMaxResults(limit).addOrder(Order.asc(sort)).list();
    }

    public Issue find(int id){
        //return (Issue)session.get(Issue.class, id);
        Transaction transaction = session.beginTransaction();
        Issue issue = (Issue)session.load(IssueHibernate.class, id);
        transaction.commit();
        return issue;
    }
    public List<Issue> find(int minId, int maxId){
        return session.createCriteria(IssueHibernate.class).add(Expression.between("id", minId, maxId)).list();
    }
    public List<Issue> findByTracker(String like){
        return session.createCriteria(IssueHibernate.class).add(Expression.like("TrackerName", like + "%")).list();
    }

    @Override
    public void update(Issue issue){
        Transaction transaction = null;
        Session session = null;
        try{
            session = new Configuration().configure("hibernate_mysql.cft.xml").buildSessionFactory().openSession(); //session = FactoryDriver.getSession("hibernate_mysql.cft.xml");
            transaction = session.beginTransaction();
            session.update( new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate()) );
            transaction.commit();
        } catch (ExceptionInInitializerError e){
            System.err.println("[update] " + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @Override
    public void delete(int id){
        Transaction transaction = null;
        try{
            session = FactoryDriver.getSession("hibernate_mysql.cft.xml");
            transaction = session.beginTransaction();
            Issue issue = (Issue)session.load(IssueHibernate.class, id);
//            Issue issue = find(id);
//            System.out.println(issue);
//            IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
            session.delete( issue ); //session.delete( issuesHibernate ); //session.delete( find(id) );
            transaction.commit();
        } catch(ExceptionInInitializerError e){
            System.out.println("[delete] " + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
    public void delete(Issue issue){
//        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(issue); //session.delete(issuesHibernate);
            transaction.commit();
        } catch(ExceptionInInitializerError e){ System.out.println("[delete] " + e.getMessage()); }
    }
}