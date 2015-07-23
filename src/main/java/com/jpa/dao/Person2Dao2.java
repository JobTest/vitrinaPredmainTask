package com.jpa.dao;

import com.jpa.domain.Person2;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by alexandr on 23.07.15.
 ** {@link https://en.wikibooks.org/wiki/Java_Persistence/Criteria}
 ** {@link http://www.baeldung.com/rest-search-language-spring-com.jpa-criteria}
 ** {@link https://github.com/JobTest/AddressBookDB/blob/master/src/main/java/com/dao/UserRolesDao.java}
 * {@link https://github.com/Home-SignUp/Jenkins-SignUp/blob/hotfix-3.1.1.4/backend/src/main/java/com/addrbook/backend/dao/PersonDaoImpl.java}
 * *******************************************************************************************************************************************
 * get|list (SELECT)
 *     save (INSERT)
 *   delete (DELETE)
 *   update (UPDATE)
 * *****************
 * use Hibernate
 */
public class Person2Dao2 {

    private Session session;

    public Person2Dao2(){}
    public Person2Dao2(Session session){
        this.session = session;
    }

    public void add(Person2 artist){
        try {
            session.getTransaction().begin();
            session.save(artist);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }
    public void add(Person2[] artists){
        try {
            session.getTransaction().begin();
            for (Person2 artist:artists)
                session.save(artist);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Person2> getAll(){
        return session.createCriteria(Person2.class).list();
    }
    public List<Person2> getAll(int limit) {
        return session.createCriteria(Person2.class).setMaxResults(limit).list();
    }
    public List<Person2> getAll(String sort){
        return session.createCriteria(Person2.class).addOrder(Order.asc(sort)).list();
    }
    public List<Person2> getAll(int limit, String sort) {
        return session.createCriteria(Person2.class).setMaxResults(limit).addOrder(Order.asc(sort)).list();
    }

    public Person2 find(int id){
        return (Person2)session.get(Person2.class, id);
    }
    public List<Person2> find(int minId, int maxId){
        return session.createCriteria(Person2.class).add(Expression.between("id", minId, maxId)).list();
    }
    public List<Person2> findBySurname(String like){
        return session.createCriteria(Person2.class).add(Expression.like("genre", like + "%")).list();
    }

    public void update(Person2 artist){
        try{
            session.getTransaction().begin();
            session.update(artist);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e){
            System.err.println(e.getMessage());
        }
    }

    public void delete(Person2 artist){
        try{
            session.getTransaction().begin();
            session.delete(artist);
            session.getTransaction().commit();
        } catch(ExceptionInInitializerError e){
            System.out.println(e.getMessage());
        }
    }
}
