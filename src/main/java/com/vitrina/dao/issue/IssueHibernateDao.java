package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueHibernate;
import com.vitrina.util.FactoryDriver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private SessionFactory factory;

    public IssueHibernateDao(){}
    public IssueHibernateDao(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void add(List<Issue> issues){
        List<IssueHibernate> issuesHibernate = new LinkedList<>();
        for (Issue issue:issues)
            issuesHibernate.add( new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate()) );
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            for (IssueHibernate issue:issuesHibernate)
                session.save(issue);
            transaction.commit();
        } catch(ExceptionInInitializerError e) {
            transaction.rollback();
            System.err.println("[add] " + e.getMessage());
        } finally {
            try {
                if (session != null && session.isOpen())
                    session.close();
            } catch (Exception e){ System.err.println("[close] " + e.getMessage()); }
        }
    }
    public void add(Issue issue){
        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(issuesHibernate);
            transaction.commit();
        } catch (ExceptionInInitializerError e) {
            transaction.rollback();
            System.err.println("[add] " + e.getMessage());
        } finally {
            try {
                if (session != null && session.isOpen())
                    session.close();
            } catch(Exception e){ System.err.println("[close] " + e.getMessage()); }
        }
    }

    @Override
    public List<Issue> getAll(List<Issue> issues){
//        return issues = session.createCriteria(IssueHibernate.class).list();

        Session session = null;
        try {
            session = factory.openSession();
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
        Session session = factory.openSession();
        List<Issue> issues = session.createCriteria(IssueHibernate.class).setMaxResults(limit).list();
        session.close();
        return issues;
    }
    public List<Issue> getAll(String sort){
        Session session = factory.openSession();
        List<Issue> issues = session.createCriteria(IssueHibernate.class).addOrder(Order.asc(sort)).list();
        session.close();
        return issues;
    }
    public List<Issue> getAll(int limit, String sort) {
        Session session = factory.openSession();
        List<Issue> issues = session.createCriteria(IssueHibernate.class).setMaxResults(limit).addOrder(Order.asc(sort)).list();
        session.close();
        return issues;
    }

    public Issue find(int id){
        //return (Issue)session.get(Issue.class, id);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Issue issue = (Issue)session.load(IssueHibernate.class, id);
        transaction.commit();
        session.close();
        return issue;
    }
    public List<Issue> find(int minId, int maxId){
        Session session = factory.openSession();
        List<Issue> issues = session.createCriteria(IssueHibernate.class).add(Expression.between("id", minId, maxId)).list();
        session.close();
        return issues;
    }
    public List<Issue> findByTracker(String like){
        Session session = factory.openSession();
        List<Issue> issues = session.createCriteria(IssueHibernate.class).add(Expression.like("TrackerName", like + "%")).list();
        session.close();
        return issues;
    }

    @Override
    public void update(Issue issue){
        Session session = null;
        Transaction transaction = null;
        try{
            session = factory.openSession(); //session = FactoryDriver.getSession("hibernate_mysql.cft.xml");
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
        Session session = null;
        Transaction transaction = null;
        try{
            session = factory.openSession();
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
        Session session = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(issue); //session.delete(issuesHibernate);
            transaction.commit();
        } catch(ExceptionInInitializerError e){
            transaction.rollback();
            System.out.println("[delete] " + e.getMessage());
        } finally {
            try {
                if (session != null && session.isOpen())
                    session.close();
            } catch (Exception e){ System.err.println("[close] " + e.getMessage()); }
        }
    }
}