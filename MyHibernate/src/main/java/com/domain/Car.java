package com.domain;

import javax.persistence.*;

/**
 * Created by alexandr on 13.08.15.
 */

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="type")
    private String type;

    @Column(name="number")
    private int number;

    @Column(name="color")
    private String color;

    public Car(){}
    public Car(String type, int number, String color){
        this.type = type;
        this.number = number;
        this.color = color;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", number=" + number +
                ", color='" + color + '\'' +
                '}';
    }
}
