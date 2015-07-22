package com.demo.dao;

/**
 * Created by alexandr on 22.07.15.
 * @author dn200978lak
 * @version 2.0
 ** {@link http://www.concretepage.com/java/jpa/java-persistence-api-example}
 ** {@link http://www.objectdb.com/java/jpa/query/named}
 ** {@link https://en.wikibooks.org/wiki/Java_Persistence/Persisting}
 ** {@link https://en.wikibooks.org/wiki/Java_Persistence/Criteria}
 * ****************************************************************
 * persist (INSERT)
 *   merge (UPDATE)
 *  remove (DELETE)
 *    find (SELECT)
 * {@link http://www.infiniteskills.com/training/hibernate-and-java-persistence-api-jpa-fundamentals/hibernate-demo.html}
 * {@link https://vaadin.com/wiki/-/wiki/main/adding+jpa+to+the+address+book+demo}
 *
 ** {@link http://www.baeldung.com/rest-search-language-spring-jpa-criteria}
 */

import com.demo.domain.Fermer;

import javax.persistence.*;
import java.util.List;

public class FermerDao {

// 	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmer");
//
//	static {
//        factory = Persistence.createEntityManagerFactory("Farmer");
//	}

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmer");

	public void add(Fermer[] fermers) {
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
        for (Fermer fermer:fermers)
            em.persist(fermer);
		em.getTransaction().commit();

		em.clear();
	}

    public void update(Fermer fermer) {
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(fermer);
        em.getTransaction().commit();

        em.clear();
    }

    public void delete(Fermer fermer) {
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(fermer);
        em.getTransaction().commit();
    }


    public void getAll() {
        EntityManager em = factory.createEntityManager();

        try {
            TypedQuery typeQuery = em.createNamedQuery("Farmer.getAll", Fermer.class);
            List<Fermer> farmers = typeQuery.getResultList();

            for(Fermer farmer:farmers)
                System.out.println(farmer);
        } catch (NoResultException e){
            return;
        }
    }

    public Fermer findByName(String name) {
        EntityManager em = factory.createEntityManager();
        Fermer    farmer = null;

        try {
            TypedQuery typeQuery = em.createNamedQuery("findByName", Fermer.class).setParameter("name",name);
            farmer = (Fermer)typeQuery.getSingleResult();
        } catch (NoResultException e){}

        return farmer;
    }

    public Fermer find(int id){
        EntityManager em = factory.createEntityManager();

        Fermer fermer = em.find(Fermer.class, id);
        em.close();

        return fermer;
    }
}
