package com.domain;

import org.hibernate.annotations.CollectionOfElements;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexandr on 13.08.15.
 */

@Entity
@Table(name = "miratex")
public class Miratex {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "friend")
    private String friend;

    //@ElementCollection
    @CollectionOfElements
    @Column(name="friends")
    private List<String> friends;


    public Miratex(){}
    public Miratex(int id, String name, String surname, String friend, List<String> friends){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.friend = friend;
        this.friends = friends;
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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getFriend() {
        return friend;
    }
    public void setFriend(String friend) {
        this.friend = friend;
    }
    public List<String> getFriends() {
        return friends;
    }
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Miratex{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", friend='" + friend + '\'' +
                ", friends=" + friends +
                '}';
    }
}
