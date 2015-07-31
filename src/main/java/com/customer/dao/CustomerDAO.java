package com.customer.dao;

import com.customer.model.Customer;

public interface CustomerDAO {

	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);

}




