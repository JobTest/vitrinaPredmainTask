package com.dao;

import com.domain.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alexandr on 13.08.15.
 */
public class DriverDao {

    public DriverDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void add(Driver driver){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }catch (Exception e1){
            try{
                transaction.rollback();
            }catch (Exception e2){}
        } finally {
            try{
                if(session != null && session.isOpen())
                    session.close();
            } catch (Exception e3){}
        }
    }

    public Driver get(int id){
        Session session = sessionFactory.openSession();
        return (Driver) session.get(Driver.class,id);
    }

    public List<Driver> getAll(){
        Session session = sessionFactory.openSession();
        return session.createCriteria(Driver.class).list();
    }

    public void update(Driver driver){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e1){
            try{
                transaction.rollback();
            }catch (Exception e2){}
        } finally {
            try{
                if(session != null && session.isOpen())
                    session.close();
            }catch (Exception e3){}
        }
    }

    public void delete(int id){
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            Driver driver = (Driver) session.get(Driver.class,id);
            transaction = session.beginTransaction();
            session.delete(driver);
            transaction.commit();
        }catch (Exception e1){
            try{
                transaction.rollback();
            }catch (Exception e2){}
        } finally{
            try{
                if(session != null && session.isOpen())
                    session.close();
            }catch (Exception e3){}
        }
    }

    private SessionFactory sessionFactory;
}
