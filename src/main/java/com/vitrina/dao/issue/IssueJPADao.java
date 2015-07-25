package com.vitrina.dao.issue;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.domain.IssueJPA;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        em.persist(issue);
    }
    public void add(List<Issue> issues){
        em.getTransaction().begin();
        for (Issue issue:issues)
            em.persist(issue);
        em.getTransaction().commit();
    }

    public List<Issue> getAll(List<Issue> select){
        TypedQuery typedQuery = em.createQuery("SELECT issue FROM IssueJPA issue", IssueJPA.class);
        return select = typedQuery.getResultList();
    }

    public IssueJPA find(int id){
        return em.find(IssueJPA.class, id);
    }

    public void update(Issue issue){
        em.getTransaction().begin();
        em.merge(issue);
        em.getTransaction().commit();
    }

    public void delete(Issue issue){
        em.getTransaction().begin();
        em.remove(issue);
        em.getTransaction().commit();
    }
    public void delete(int id){
        em.getTransaction().begin();
        em.remove( em.find(IssueJPA.class,id) );
        em.getTransaction().commit();
    }

}
