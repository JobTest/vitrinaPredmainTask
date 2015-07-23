package com.jpa.dao;

import com.jpa.domain.Artist2;

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
public class Artist2Dao {

    private EntityManager em;

    public Artist2Dao(){}
    public Artist2Dao(EntityManager em){
        this.em = em;
    }

    public void add(Artist2 artist){
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
    }
    public void add(Artist2[] artists){
        em.getTransaction().begin();
        for (Artist2 artist:artists)
            em.persist(artist);
        em.getTransaction().commit();
    }

    public List<Artist2> getAll(){
        TypedQuery typedQuery = em.createNamedQuery("Artist2.findAll", Artist2.class);
        return typedQuery.getResultList();
    }

    public Artist2 find(int id){
        return em.find(Artist2.class, id);
    }
    public Artist2 findByName(String name){
        TypedQuery typedQuery = em.createNamedQuery("Artist2.findByName", Artist2.class).setParameter("name", name);
        return (Artist2)typedQuery.getSingleResult();
    }
    public List<Artist2> findBySurname(String surname){
        TypedQuery typedQuery = em.createNamedQuery("Artist2.findBySurname", Artist2.class).setParameter("surname", surname);
        return typedQuery.getResultList();
    }

    public void update(Artist2 artist){
        em.getTransaction().begin();
        em.merge(artist);
        em.getTransaction().commit();
    }

    public void delete(Artist2 artist){
        em.getTransaction().begin();
        em.remove(artist);
        em.getTransaction().commit();
    }
}
