package com.jpa.service;

import com.jpa.dao.Person2Dao;
import com.jpa.domain.Person2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alexandr on 23.07.15.
 */
public class Service2 {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Person2");
        EntityManager             em = factory.createEntityManager();

        service(new Person2Dao( em ));

        em.clear();
        em.close();
        factory.close();
    }

    public static void service(Person2Dao service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Person2[] artists = {new Person2("Sasha","Lazarchuk"), new Person2("Yana","Barusina"), new Person2("Jenya","Barusina"), new Person2("Alesya","Lazarchuk")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Person2 artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println( service.find(2) );

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-BY-NAME ]");
        System.out.println( service.findByName("Sasha") );

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-BY-SURNAME ]");
        for (Person2 artist:service.findBySurname("Barusina"))
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Person2 artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );
        for (Person2 artist:service.getAll())
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        service.delete( service.findByName("Jenya") );
        for (Person2 artist:service.getAll())
            System.out.println( artist );
    }
}
