package com.customer.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.customer.dao.CustomerDAO;
import com.customer.model.Customer;

/**
 * Created by alexandr on 31.07.15.
 * {@link http://www.mkyong.com/spring/maven-spring-jdbc-example/}
 * ***************************************************************
 * Spring + JDBC example
 */
public class App {

    public static void main(String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	 
        CustomerDAO    customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer          customer = new Customer(1, "mkyong",28);
        customerDAO.insert(customer);

        System.out.println( customerDAO.findByCustomerId(1) );
    }

}
