package com.jpa.domain;

import javax.persistence.*;

/**
* Created by alexandr on 23.07.15.
*/

@Entity
@Table(name = "artist2")
@NamedQueries({
        @NamedQuery(name = "Artist2.findAll", query = "SELECT artist FROM Artist2 artist"),
        @NamedQuery(name = "Artist2.findByName", query = "SELECT artist FROM Artist2 artist WHERE artist.name = :name"),
        @NamedQuery(name = "Artist2.findBySurname", query = "SELECT artist FROM Artist2 artist WHERE artist.genre = :surname")
})
public class Artist2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "surname", length = 20)
    private String genre;

    public Artist2(){}
    public Artist2(String name, String genre){
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
        return "Artist2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
