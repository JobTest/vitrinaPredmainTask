package com.vitrina.dao;

import com.vitrina.domain.Issue;
import com.vitrina.domain.Issue2;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
* Created by alexandr on 24.07.15.
*/
public class Issue2Dao implements IssueDao {

    EntityManager em;

    public Issue2Dao(){}
    public Issue2Dao(EntityManager em){
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
        TypedQuery typedQuery = em.createQuery("SELECT issue FROM Issue2 issue", Issue2.class);
        return select = typedQuery.getResultList();
    }

    public Issue2 find(int id){
        return em.find(Issue2.class, id);
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
        em.remove( em.find(Issue2.class,id) );
        em.getTransaction().commit();
    }

}
