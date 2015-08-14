package com.controller;

import com.domain.Employee;
import com.domain.Meeting;
import com.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Саша on 14.08.2015.
 * {@link http://viralpatel.net/blogs/hibernate-many-to-many-xml-mapping-example/}
 */
public class MainTest {

    @Before
    public void setUp(){}

    @Test
    public void testMain(){
        Meeting meeting1 = new Meeting("Quaterly Sales meeting");
        Meeting meeting2 = new Meeting("Weekly Status meeting");
        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");
        employee1.getMeetings().add(meeting1);
        employee1.getMeetings().add(meeting2);
        employee2.getMeetings().add(meeting1);


        SessionFactory  factory = HibernateUtil.getSessionFactory();
        Session         session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(employee1);
            session.save(employee2);
            transaction.commit();
        }catch (Exception e1){
            System.err.println( e1.getMessage() );
            try{
                transaction.rollback();
            }catch(Exception e2){}
        }finally{
            try{
                if( session!=null && session.isOpen() )
                    session.close();
            }catch (Exception e3){}
        }
    }

    /**
     * ehCache | hibernate cache configuration file | hibernate configuration file for mysql
     * *************************************************************************************
     * {@link http://www.tutorialspoint.com/hibernate/hibernate_caching.htm}
     * {@link http://howtodoinjava.com/2013/07/04/hibernate-ehcache-configuration-tutorial/}
     * {@link http://www.1maven.com/idartifact/org.hibernate:hibernate-ehcache:3.6.8.Final}
     *
     * {@link http://forum.spring.io/forum/spring-projects/data/1354-how-do-i-setup-ehcache-with-spring-and-hibernate}
     * {@link http://www.mkyong.com/ehcache/ehcache-hello-world-example/}
     */
//    @Test
//    public void testCache(){
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        Session        session = factory.openSession();
//
//        Query query = session.createQuery("SELECT employee FROM Employee employee");
//        query.setCacheable(true);
//        query.setCacheRegion("employee");
//        List<Employee> employees = query.list();
//        for (Employee employee:employees)
//            System.out.println(employee);
//
//        session.close();
//    }

}
