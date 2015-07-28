package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueJPA;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
* Created by alexandr on 24.07.15.
*/
public class IssueJPADao implements IssueDao {

    EntityManager em;

    public IssueJPADao(){}
    public IssueJPADao(EntityManager em){
        this.em = em;
    }

    public void add(Issue issue){
        IssueJPA issueJPA = new IssueJPA(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());

        em.getTransaction().begin();
        em.persist(issueJPA);
        em.getTransaction().commit();
    }
    public void add(List<Issue> issues){
        List<IssueJPA> issuesJPA = new LinkedList<>();
        for (Issue issue:issues)
            issuesJPA.add( new IssueJPA(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate()) );

        em.getTransaction().begin();
        for (IssueJPA issue:issuesJPA)
            em.persist(issue);
        em.getTransaction().commit();
    }

    public List<Issue> getAll(List<Issue> select){
        TypedQuery typedQuery = em.createQuery("SELECT issue FROM IssueJPA issue", Issue.class);
        return select = typedQuery.getResultList();
    }

    public IssueJPA find(int id){
        return em.find(IssueJPA.class, id);
    }

    public void update(Issue issue){
        IssueJPA issueJPA = new IssueJPA(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());

        em.getTransaction().begin();
        em.merge(issueJPA);
        em.getTransaction().commit();
    }

    public void delete(Issue issue){
        IssueJPA issueJPA = new IssueJPA(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());

        em.getTransaction().begin();
        em.remove(issueJPA);
        em.getTransaction().commit();
    }
    public void delete(int id){
        Issue issue = em.find(IssueJPA.class, id);
        IssueJPA issueJPA = new IssueJPA(issue.getId(),issue.getParentId(),issue.getProjectId(),issue.getProjectName(),issue.getTrackerId(),issue.getTrackerName(),issue.getStatusId(),issue.getStatusName(),issue.getFixedVersionId(),issue.getFixedVersionName(),issue.getSubject(),issue.getStartDate(),issue.getDueDate());

        em.getTransaction().begin();
        em.remove(issueJPA);
        em.getTransaction().commit();
    }

}
