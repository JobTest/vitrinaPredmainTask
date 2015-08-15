package com.journaldev.hibernate;

import com.journaldev.hibernate.main.App;
import com.journaldev.hibernate.model.Employee;
import com.journaldev.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Саша on 14.08.2015.
 * {@link http://www.journaldev.com/2980/hibernate-ehcache-second-level-caching-example-tutorial}
 * **********************************************************************************************
 * Hibernate EHCache Project
 */
public class AppTest {

    @Before
    public void setUp(){
        app = new App();
    }

    @Test
    public void testMain(){
        System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

		/* Initialize Sessions */
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Statistics statistics = factory.getStatistics();
        System.out.println("Statistics enabled = " + statistics.isStatisticsEnabled());
        statistics.setStatisticsEnabled(true);
        System.out.println("Statistics enabled = " + statistics.isStatisticsEnabled());

        Session session = factory.openSession();
        Session         otherSession = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();

        app.printStatistics(statistics, 0);

        Employee employee = (Employee) session.load(Employee.class,1L);
        app.printEmployee(employee, statistics, 1);

        employee = (Employee) session.load(Employee.class,1L);
        app.printEmployee(employee, statistics, 2);

		/* clear first level cache, so that second level cache is used */
        session.evict(employee);
        employee = (Employee) session.load(Employee.class,1L);
        app.printEmployee(employee, statistics, 3);

        employee = (Employee) session.load(Employee.class,3L);
        app.printEmployee(employee, statistics, 4);

        employee = (Employee) otherSession.load(Employee.class,1L);
        app.printEmployee(employee, statistics, 5);

		/* Release resources */
        transaction.commit();
        otherTransaction.commit();
        factory.close();
    }

    App app;
}
