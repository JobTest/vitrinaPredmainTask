package com.jpa.service;

import com.jpa.dao.ArtistDao;
import com.jpa.domain.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alexandr on 23.07.15
 */
public class Service {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Artist");
        EntityManager             em = factory.createEntityManager();

        service(new ArtistDao(em));

        em.clear();
        em.close();
        factory.close();
    }

    public static void service(ArtistDao service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Artist[] artists = {new Artist(1,"Sasha","Lazarchuk"), new Artist(2,"Yana","Barusina"), new Artist(3,"Jenya","Barusina"), new Artist(4,"Alesya","Lazarchuk")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Artist artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println(service.find(4));

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Artist artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );

        for (Artist artist:service.getAll())
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        Artist artist3 = service.find(3);
        service.delete(artist3);

        for (Artist artist:service.getAll())
            System.out.println(artist);
    }

}
