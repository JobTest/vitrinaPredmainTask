package com.jpa.dao;

import com.jpa.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alexandr on 23.07.15.
 * @author dn200978lak
 * @version 2.1
 ** {@link http://com.jpa-basics-tutorial.googlecode.com/svn/trunk/}
 ** {@link http://www.concretepage.com/java/com.jpa/java-persistence-api-example}
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
 */
public class PersonDao {

    EntityManager em;

    public PersonDao(){}
    public PersonDao(EntityManager em){
        this.em = em;
    }

    public void add(Person artist){
        em.persist(artist);
    }
    public void add(Person[] artists){
        em.getTransaction().begin();
        for (Person artist:artists)
            em.persist(artist);
        em.getTransaction().commit();
    }

    public List<Person> getAll(){
        TypedQuery typedQuery = em.createQuery("SELECT artist FROM Person artist", Person.class);
        return typedQuery.getResultList();
    }

    public Person find(int id){
        return em.find(Person.class, id);
    }

    public void update(Person artist){
        em.getTransaction().begin();
        em.merge(artist);
        em.getTransaction().commit();
    }

    public void delete(Person artist){
        em.getTransaction().begin();
        em.remove(artist);
        em.getTransaction().commit();
    }
}
