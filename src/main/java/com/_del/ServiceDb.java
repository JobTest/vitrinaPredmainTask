//package com;
//
///**
// * Created by alexandr on 15.07.15.
// */
//import com.vitrina.domain.Issue;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//import java.util.Iterator;
//import java.util.List;
//
///**
// *
// * @author dn200978lak
// * @version 2.0
// *
// *
// *
// * <issues type="array" limit="100" total_count="132" offset="0">
// * http://10.1.99.58/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
// *
// */
//public class ServiceDb {
//
//    public EntityManager em = Persistence.createEntityManagerFactory("VITRINA").createEntityManager();
//
//    /**
//     * @param issue
//     * @return Issue
//     */
//    public Issue add(Issue issue){
//        em.getTransaction().begin();
//        Issue issueFromDB = em.merge(issue);
//        em.getTransaction().commit();
//        return issueFromDB;
//    }
//
//    /**
//     * @param id
//     */
//    public void delete(long id){
//        em.remove( get(id) );
//    }
//
//    public void clear(){
//        System.out.print("DB Clear ................. ");
//        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);
//        List<Issue>       db         = namedQuery.getResultList();
//        Iterator idb = db.iterator();
//        while (idb.hasNext()){
//            em.remove( (Issue)idb.next() );
//        }
//        System.out.println("OK");
//    }
//
//    /**
//     * @param id
//     * @return Issue
//     */
//    public Issue get(long id){
//        return em.find(Issue.class, id);
//    }
//
//    /**
//     * @param issue
//     */
//    public void update(Issue issue){
//        em.getTransaction().begin();
//        em.merge(issue);
//        em.getTransaction().commit();
//    }
//
//    /**
//     * @return List<Issue>
//     */
//    public List<Issue> getIssues(){
//        TypedQuery<Issue> namedQuery = em.createNamedQuery("Issue.getAll", Issue.class);
//        return namedQuery.getResultList();
//    }
//
//}
