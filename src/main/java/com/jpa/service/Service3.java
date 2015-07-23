package com.jpa.service;

import com.jpa.dao.Artist2Dao2;
import com.jpa.domain.Artist2;
import com.jpa.util.DataFactory;
import org.hibernate.Session;

/**
 * Created by alexandr on 23.07.15.
 */
public class Service3 {

    public static void main(String[] args) {
        Session session = DataFactory.getInstance().openSession();

        service( new Artist2Dao2(session) );

        session.close();
    }

    public static void service(Artist2Dao2 service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Artist2[] artists = {new Artist2("Sasha3","Lazarchuk3"), new Artist2("Yana3","Barusina3"), new Artist2("Jenya3","Barusina3"), new Artist2("Alesya3","Lazarchuk3")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Artist2 artist:service.getAll("genre")) //for (Artist2 artist:service.getAll(2))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println(service.find(2));

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-MIN-MAX ]");
        for (Artist2 artist:service.find(3,4))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-SURNAME ]");
        for (Artist2 artist:service.findBySurname("Lazarchuk"))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Artist2 artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );

        for (Artist2 artist:service.getAll())
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        service.delete( service.find(3) );

        for (Artist2 artist:service.getAll())
            System.out.println( artist );
    }
}
