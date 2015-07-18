package com.vitrina.dao;

import com.vitrina.domain.Issue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandr on 15.07.15.
 * @author dn200978lak
 * @version 2.0
 * **************************************************************
 * <issues type="array" limit="100" total_count="132" offset="0">
 * http://10.1.99.58/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
 */
public class IssueDao2 {


    public EntityManagerFactory factory;
    public EntityManager em;
//    public EntityManager em = Persistence.createEntityManagerFactory("VITRINA").createEntityManager();

    public IssueDao2(){
        factory = Persistence.createEntityManagerFactory("VITRINA");
        em = factory.createEntityManager();
    }

    public Issue add(Issue issue){
        em.getTransaction().begin();
        Issue add = em.merge(issue);
        em.getTransaction().commit();

        return add;
    }

    public void delete(long id){
        em.remove( get(id) );
    }

    public void clear(){
        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);
        List<Issue>               db = namedQuery.getResultList();
        Iterator                 idb = db.iterator();
        while (idb.hasNext())
            em.remove( (Issue)idb.next() );
    }

    public Issue get(long id){
        return em.find(Issue.class, id);
    }

    public void update(Issue issue){
        em.getTransaction().begin();
        em.merge(issue);
        em.getTransaction().commit();
    }

    public List<Issue> getIssues(){
        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);

        return namedQuery.getResultList();
    }

}
