package com.vitrina.service;

import com.vitrina.entity.Issue;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
public class ServiceDb {

    public EntityManager em = Persistence.createEntityManagerFactory("VITRINA").createEntityManager();

    /**
     * @param issue
     * @return Issue
     */
    public Issue add(Issue issue){
        em.getTransaction().begin();
        Issue issueFromDB = em.merge(issue);
        em.getTransaction().commit();
        return issueFromDB;
    }

    /**
     * @param id
     */
    public void delete(long id){
        em.remove( get(id) );
    }

    public void clear(){
        System.out.print("DB Clear ................. ");
        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);
        List<Issue>       db         = namedQuery.getResultList();
        Iterator idb = db.iterator();
        while (idb.hasNext()){
            em.remove( (Issue)idb.next() );
        }
        System.out.println("OK");
    }

    /**
     * @param id
     * @return Issue
     */
    public Issue get(long id){
        return em.find(Issue.class, id);
    }

    /**
     * @param issue
     */
    public void update(Issue issue){
        em.getTransaction().begin();
        em.merge(issue);
        em.getTransaction().commit();
    }

    /**
     * @return List<Issue>
     */
    public List<Issue> getIssues(){
        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);
        return namedQuery.getResultList();
    }

}