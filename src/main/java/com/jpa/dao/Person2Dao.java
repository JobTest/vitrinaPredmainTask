package com.jpa.dao;

import com.jpa.domain.Person2;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alexandr on 23.07.15.
 * @author dn200978lak
 * @version 2.2
 ** {@link http://www.objectdb.com/java/com.jpa/query/named}
 ** {@link https://en.wikibooks.org/wiki/Java_Persistence/Persisting}
 * ******************************************************************
 *    find (SELECT)
 * persist (INSERT)
 *  remove (DELETE)
 *   merge (UPDATE)
 *
 * {@link http://www.infiniteskills.com/training/hibernate-and-java-persistence-api-com.jpa-fundamentals/hibernate-demo.html}
 * {@link https://vaadin.com/wiki/-/wiki/main/adding+com.jpa+to+the+address+book+demo}
 * *******************************************************************************
 * use Persistence API
 * @NamedQueries({ @NamedQuery(...), @NamedQuery(...) })
 */
public class Person2Dao {

    private EntityManager em;

    public Person2Dao(){}
    public Person2Dao(EntityManager em){
        this.em = em;
    }

    public void add(Person2 artist){
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
    }
    public void add(Person2[] artists){
        em.getTransaction().begin();
        for (Person2 artist:artists)
            em.persist(artist);
        em.getTransaction().commit();
    }

    public List<Person2> getAll(){
        TypedQuery typedQuery = em.createNamedQuery("Person2.findAll", Person2.class);
        return typedQuery.getResultList();
    }

    public Person2 find(int id){
        return em.find(Person2.class, id);
    }
    public Person2 findByName(String name){
        TypedQuery typedQuery = em.createNamedQuery("Person2.findByName", Person2.class).setParameter("name", name);
        return (Person2)typedQuery.getSingleResult();
    }
    public List<Person2> findBySurname(String surname){
        TypedQuery typedQuery = em.createNamedQuery("Person2.findBySurname", Person2.class).setParameter("surname", surname);
        return typedQuery.getResultList();
    }

    public void update(Person2 artist){
        em.getTransaction().begin();
        em.merge(artist);
        em.getTransaction().commit();
    }

    public void delete(Person2 artist){
        em.getTransaction().begin();
        em.remove(artist);
        em.getTransaction().commit();
    }
}
