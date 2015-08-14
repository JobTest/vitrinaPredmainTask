package com.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexandr on 13.08.15.
 */

@Entity
@Table(name="driver")
public class Driver {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    /* http://www.mkyong.com/hibernate/hibernate-many-to-many-relationship-example-annotation/ */
//    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//    private Car car;
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="owner")
    private Car car;
//    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//    @JoinTable(name="owner")
//    private List<Car> cars;

    public Driver(){}
    public Driver(String name, String address, Car car){
        this.name = name;
        this.address = address;
        this.car = car;
    }
//    public Driver(String name, String address, List<Car> cars){
//        this.name = name;
//        this.address = address;
//        this.cars = cars;
//    }

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
//    public List<Car> getCars() {
//        return cars;
//    }
//    public void setCars(List<Car> cars) {
//        this.cars = cars;
//    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", car=" + car +
//                ", cars=" + cars +
                '}';
    }
}
