package com.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by alexandr on 23.07.15.
 */
@Entity
public class Person {

    @Id
    private int id;
    private String name;
    private String genre;

    public Person(){}
    public Person(int id, String name, String genre){
        this.id = id;
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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
