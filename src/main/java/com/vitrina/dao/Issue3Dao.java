package com.vitrina.dao;

import com.vitrina.domain.Issue3;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by alexandr on 24.07.15.
 */
public class Issue3Dao {

    private Session session;

    public Issue3Dao(){}
    public Issue3Dao(Session session){
        this.session = session;
    }

    public void add(Issue3 issue){
        try {
            session.getTransaction().begin();
            session.save(issue);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }
    public void add(Issue3[] issues){
        try {
            session.getTransaction().begin();
            for (Issue3 issue:issues)
                session.save(issue);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Issue3> getAll(){
        return session.createCriteria(Issue3.class).list();
    }
    public List<Issue3> getAll(int limit) {
        return session.createCriteria(Issue3.class).setMaxResults(limit).list();
    }
    public List<Issue3> getAll(String sort){
        return session.createCriteria(Issue3.class).addOrder(Order.asc(sort)).list();
    }
    public List<Issue3> getAll(int limit, String sort) {
        return session.createCriteria(Issue3.class).setMaxResults(limit).addOrder(Order.asc(sort)).list();
    }

    public Issue3 find(int id){
        return (Issue3)session.get(Issue3.class, id);
    }
    public List<Issue3> find(int minId, int maxId){
        return session.createCriteria(Issue3.class).add(Expression.between("id", minId, maxId)).list();
    }
    public List<Issue3> findByTracker(String like){
        return session.createCriteria(Issue3.class).add(Expression.like("TrackerName", like + "%")).list();
    }

    public void update(Issue3 issue){
        try{
            session.getTransaction().begin();
            session.update(issue);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e){
            System.err.println(e.getMessage());
        }
    }

    public void delete(Issue3 issue){
        try{
            session.getTransaction().begin();
            session.delete(issue);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){
            System.out.println(e.getMessage());
        }
    }
}