package com.jpa.domain;

import javax.persistence.*;

/**
* Created by alexandr on 23.07.15.
*/

@Entity
@Table(name = "person2")
@NamedQueries({
        @NamedQuery(name = "Person2.findAll", query = "SELECT person FROM Person2 person"),
        @NamedQuery(name = "Person2.findByName", query = "SELECT person FROM Person2 person WHERE person.name = :name"),
        @NamedQuery(name = "Person2.findBySurname", query = "SELECT person FROM Person2 person WHERE person.genre = :surname")
})
public class Person2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "surname", length = 20)
    private String genre;

    public Person2(){}
    public Person2(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
