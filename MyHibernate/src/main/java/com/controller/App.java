package com.controller;

import com.domain.Car;
import com.domain.Driver;
import com.domain.Miratex;
import com.services.ServiceDriver;
import com.services.ServiceMiratex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 13.08.15.
 * ********************************
 * {@link http://habrahabr.ru/post/29694/}
 * {@link http://hibernate.org/orm/tooling/}
 * {@link http://habrahabr.ru/post/186078/}
 *
 * {@link http://habrahabr.ru/post/259005/}
 * {@link https://dev.1c-bitrix.ru/learning/course/?COURSE_ID=43&LESSON_ID=5824}
 * {@link http://habrahabr.ru/post/132385/}
 * {@link http://stackoverflow.com/questions/1360606/persist-collection-fields-with-hibernate}
 * {@link http://stackoverflow.com/questions/5867130/hibernate-liststring}
 * {@link http://www.mkyong.com/hibernate/hibernate-many-to-many-relationship-example-annotation/}
 */
public class App {

    public static void main(String[] args) {
        /* *****************[ 1 ]****************** */
//        List<String> friends = new ArrayList<>();
//        friends.add("Marina");
//        friends.add("Aleg");
//        friends.add("Dnirty");
//        friends.add("Toni");
//        friends.add("Michael");
//
//        Miratex miratex1 = new Miratex(1,"Sasha","Lazarchuk","Sergey",friends);
//        Miratex miratex2 = new Miratex(2,"Any","Nenene","Alexey",friends);
//        Miratex miratex3 = new Miratex(3,"Jeny","Barusina","Olya",friends);
//        service.add(miratex1);
//        service.add(miratex2);
//        service.add(miratex3);
//
//        Miratex miratexX = service.get(1);
//        System.out.println("ID: " +  miratexX);
//
//        miratexX.setFriend("Vova");
//        service.update(miratexX);
//
//        service.delete(1);
//
//        for (Miratex miratex:service.getAll())
//            System.out.println("ID: " +  miratex);

        /* *****************[ 2 ]****************** */
        Car car1 = new Car("road", 123, "white");
        Car car2 = new Car("baggy", 456, "dark");
        Car car3 = new Car("speed", 789, "red");
        List<Car>cars1 = new LinkedList<>();
        List<Car>cars2 = new LinkedList<>();
        List<Car>cars3 = new LinkedList<>();
        cars1.add(car1);
        cars2.add(car1);
        cars2.add(car2);
        cars3.add(car1);
        cars3.add(car2);
        cars3.add(car3);
        Driver driver1 = new Driver("Sasha","Nikolaev",car1);
        Driver driver2 = new Driver("Yana","Nikolaev",car2);
        Driver driver3 = new Driver("Nick","Nikolaev",car3);
//        Driver driver1 = new Driver("Sasha","Nikolaev",cars1);
//        Driver driver2 = new Driver("Yana","Nikolaev",cars2);
//        Driver driver3 = new Driver("Nick","Nikolaev",cars3);
        service2.add(driver1);
        service2.add(driver2);
        service2.add(driver3);

        for(Driver driver:service2.getAll())
            System.out.println("ID: " + driver);
    }

    public static ServiceMiratex service = new ServiceMiratex();
    public static ServiceDriver service2 = new ServiceDriver();
}
