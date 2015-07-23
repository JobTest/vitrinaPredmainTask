package com.jpa.service;

import com.jpa.dao.Artist2Dao;
import com.jpa.domain.Artist2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alexandr on 23.07.15.
 */
public class Service2 {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Artist2");
        EntityManager             em = factory.createEntityManager();

        service(new Artist2Dao( em ));

        em.clear();
        em.close();
        factory.close();
    }

    public static void service(Artist2Dao service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Artist2[] artists = {new Artist2("Sasha","Lazarchuk"), new Artist2("Yana","Barusina"), new Artist2("Jenya","Barusina"), new Artist2("Alesya","Lazarchuk")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Artist2 artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println( service.find(2) );

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-BY-NAME ]");
        System.out.println( service.findByName("Sasha") );

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-BY-SURNAME ]");
        for (Artist2 artist:service.findBySurname("Barusina"))
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Artist2 artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );
        for (Artist2 artist:service.getAll())
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        service.delete( service.findByName("Jenya") );
        for (Artist2 artist:service.getAll())
            System.out.println( artist );
    }
}
