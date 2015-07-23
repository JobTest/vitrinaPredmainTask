package com.jpa.service;

import com.jpa.dao.Person2Dao2;
import com.jpa.domain.Person2;
import com.jpa.util.DataFactory;
import org.hibernate.Session;

/**
 * Created by alexandr on 23.07.15.
 */
public class Service3 {

    public static void main(String[] args) {
        Session session = DataFactory.getInstance().openSession();

        service( new Person2Dao2(session) );

        session.close();
    }

    public static void service(Person2Dao2 service){
        System.out.println("-------------------------------------------------------------------------------------------[ ADD ]");
        Person2[] artists = {new Person2("Sasha3","Lazarchuk3"), new Person2("Yana3","Barusina3"), new Person2("Jenya3","Barusina3"), new Person2("Alesya3","Lazarchuk3")};
        service.add(artists);

        System.out.println("-------------------------------------------------------------------------------------------[ PRINT ]");
        for (Person2 artist:service.getAll("genre")) //for (Artist2 artist:service.getAll(2))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND ]");
        System.out.println(service.find(2));

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-MIN-MAX ]");
        for (Person2 artist:service.find(3,4))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ FIND-SURNAME ]");
        for (Person2 artist:service.findBySurname("Lazarchuk"))
            System.out.println(artist);

        System.out.println("-------------------------------------------------------------------------------------------[ UPDATE ]");
        Person2 artist2 = service.find(2);
        artist2.setGenre("Lazarchuk");
        service.update( artist2 );

        for (Person2 artist:service.getAll())
            System.out.println( artist );

        System.out.println("-------------------------------------------------------------------------------------------[ DELETE ]");
        service.delete( service.find(3) );

        for (Person2 artist:service.getAll())
            System.out.println( artist );
    }
}
