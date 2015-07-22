package com.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="farmer")
@NamedQueries({
        @NamedQuery(name = "Farmer.getAll", query = "SELECT farmer FROM Fermer farmer"),
        @NamedQuery(name = "Farmer.findAll", query = "SELECT farmer FROM Fermer farmer"),
        @NamedQuery(name = "findByName", query = "SELECT farmer FROM Fermer farmer WHERE farmer.name = :name")
})
public class Fermer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="village")
	private String village;


    public Fermer(){}
    public Fermer(String name, String village){
        this.name = name;
        this.village = village;
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
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", village='" + village + '\'' +
                '}';
    }
}
