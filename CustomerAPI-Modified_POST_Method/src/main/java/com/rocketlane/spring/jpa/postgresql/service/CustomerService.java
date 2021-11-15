package com.rocketlane.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rocketlane.spring.jpa.postgresql.model.Customer;

@Component
public interface CustomerService {

	public List<Customer> getAll();

	public Customer add(Customer customer);

	public Customer update(Customer customer) throws Exception;

	public Customer delete(long id);

	public Optional<Customer> getCustomerById(long id);
	
	public long checkEmailId(String emailId);
}
