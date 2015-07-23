package com.jpa.service;

import com.jpa.dao.PersonDao;
import com.jpa.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alexandr on 23.07.15
 */
public class Service {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Person");
        EntityManager             em = factory.createEntityManager();

        service(new PersonDao(em));

        em.clear();
        em.close();
        factory.close();
    }

    public static void service(PersonDao service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Person[] artists = {new Person(1,"Sasha","Lazarchuk"), new Person(2,"Yana","Barusina"), new Person(3,"Jenya","Barusina"), new Person(4,"Alesya","Lazarchuk")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Person artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println(service.find(4));

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Person artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );

        for (Person artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        Person artist3 = service.find(3);
        service.delete(artist3);

        for (Person artist:service.getAll())
            System.out.println(artist);
    }

}
