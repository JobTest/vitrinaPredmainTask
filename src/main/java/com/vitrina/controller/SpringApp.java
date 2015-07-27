package com.vitrina.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Саша on 28.07.2015.
 * {@link http://crunchify.com/simplest-spring-mvc-hello-world-example-tutorial-spring-model-view-controller-tips/}
 * {@link http://www.tutorialspoint.com/spring/spring_hello_world_example.htm}
 ** {@link http://www.mkyong.com/spring/quick-start-maven-spring-example/}
 * ***********************************************************************
 * Spring - Hello World Example
 */
public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        App app = (App)context.getBean("App");
        app.start();
    }

}
