package com.vitrina.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Саша on 28.07.2015.
 * {@link http://crunchify.com/simplest-spring-mvc-hello-world-example-tutorial-spring-model-view-controller-tips/}
 * {@link http://www.tutorialspoint.com/spring/spring_hello_world_example.htm}
 ** {@link http://www.mkyong.com/spring/quick-start-maven-spring-example/}
 * ***********************************************************************
 * Spring - Hello World Example
 *
 * {@link http://spring-projects.ru/guides/relational-data-access/}
 * {@link http://javatalks.ru/topics/25965}
 */
public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring_Module.xml"); //ApplicationContext context = new MyContext("Spring_Module.xml");
        VitrinaPredmainApp app = (VitrinaPredmainApp)context.getBean("App");
        app.start();
    }

}


//class MyContext extends ClassPathXmlApplicationContext {
//
//    public MyContext(String xml){
//        super(xml);
//    }
//
//}