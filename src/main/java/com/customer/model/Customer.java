package com.customer.model;

public class Customer {

	public Customer(int custId, String name, int age) {
		this.custId = custId;
		this.name   = name;
		this.age    = age;
	}


    public void setCustId(int custId) {
        this.custId = custId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
	public int getCustId() {
		return custId;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Customer [age=" + age + ", custId=" + custId + ", name=" + name + "]";
	}

    int  custId;
    String name;
    int     age;
}
