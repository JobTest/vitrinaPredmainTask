package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

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
            session.getTransaction().begin();
            for (IssueHibernate issue:issuesHibernate)
                session.save(issue);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e) {
            System.err.println("--add-- " + e.getMessage());
        }
    }
    public void add(Issue issue){
        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        try {
            Transaction transaction = session.beginTransaction();
            session.save(issuesHibernate);
            transaction.commit();
        } catch (ExceptionInInitializerError e) {
            System.err.println("--add-- " + e.getMessage());
        }
    }

    @Override
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
        //return (Issue)session.get(Issue.class, id);
        Transaction transaction = session.beginTransaction();
        Issue issue = (Issue)session.load(Issue.class, id);
        transaction.commit();
        return issue;
    }
    public List<Issue> find(int minId, int maxId){
        return session.createCriteria(Issue.class).add(Expression.between("id", minId, maxId)).list();
    }
    public List<Issue> findByTracker(String like){
        return session.createCriteria(Issue.class).add(Expression.like("TrackerName", like + "%")).list();
    }

    public void update(Issue issue){
        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        try{
            session.getTransaction().begin();
            session.update(issuesHibernate);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e){
            System.err.println("--update-- " + e.getMessage());
        }
    }

    @Override
    public void delete(int id){
        try{
            Issue issue = find(id);
            IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
            session.getTransaction().begin();
            session.delete( issuesHibernate ); //session.delete( find(id) );
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){ System.out.println("--delete-- " + e.getMessage()); }
    }
    public void delete(Issue issue){
        IssueHibernate issuesHibernate = new IssueHibernate(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());
        try{
            session.getTransaction().begin();
            session.delete(issuesHibernate);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){ System.out.println("--delete-- " + e.getMessage()); }
    }
}