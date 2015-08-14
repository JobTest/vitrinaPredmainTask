//package com.controller;
//
//import com.domain.Employee;
//import com.domain.Meeting;
//import com.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
///**
// * Created by Саша on 14.08.2015.
// * {@link http://viralpatel.net/blogs/hibernate-many-to-many-xml-mapping-example/}
// */
//public class Main {
//
//    public static void main(String[] args) {
//        Meeting meeting1 = new Meeting("Quaterly Sales meeting");
//        Meeting meeting2 = new Meeting("Weekly Status meeting");
//        Employee employee1 = new Employee("Sergey", "Brin");
//        Employee employee2 = new Employee("Larry", "Page");
//        employee1.getMeetings().add(meeting1);
//        employee1.getMeetings().add(meeting2);
//        employee2.getMeetings().add(meeting1);
//
//
//        SessionFactory  factory = HibernateUtil.getSessionFactory();
//        Session         session = factory.openSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            session.save(employee1);
//            session.save(employee2);
//            transaction.commit();
//        }catch (Exception e1){
//            System.err.println( e1.getMessage() );
//            try{
//                transaction.rollback();
//            }catch(Exception e2){}
//        }finally{
//            try{
//                if( session!=null && session.isOpen() )
//                    session.close();
//            }catch (Exception e3){}
//        }
//    }
//
//}
