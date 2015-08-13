package com.dao;

import com.domain.Miratex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexandr on 13.08.15.
 */
public class MiratexDao {

    public MiratexDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void add(Miratex miratex){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(miratex);
            transaction.commit();
        } catch (Exception e1){
            try{
                transaction.rollback();
            } catch (Exception e2){}
        } finally {
            try {
                if(session != null && session.isOpen())
                    session.close();
            }catch (Exception e3){}
        }
    }
    public Miratex get(int id){
        Session session = sessionFactory.openSession();
        return  (Miratex) session.get(Miratex.class, id);
    }
    public List<Miratex> getAll(){
        Session session = sessionFactory.openSession();
        return session.createCriteria(Miratex.class).list();
    }
    public void update(Miratex miratex){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(miratex);
            transaction.commit();
        } catch(Exception e1){
            try {
                transaction.rollback();
            } catch(Exception e2){}
        } finally{
            try {
                if(session != null && session.isOpen())
                    session.close();
            } catch(Exception e3){}
        }
    }
    public void delete(int id){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            Miratex miratex = (Miratex) session.get(Miratex.class,id);
            transaction = session.beginTransaction();
            session.delete(miratex);
            transaction.commit();
        } catch (Exception e1){
            try{
                transaction.rollback();
            } catch (Exception e2){}
        } finally{
            try{
                if(session != null && session.isOpen())
                    session.close();
            }catch (Exception e3){}
        }
    }

    private SessionFactory sessionFactory;
}
