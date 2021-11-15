package com.rocketlane.spring.jpa.postgresql.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rocketlane.spring.jpa.postgresql.model.Customer;
import com.rocketlane.spring.jpa.postgresql.repository.CustomerRepository;
import com.rocketlane.spring.jpa.postgresql.service.CustomerService;

@Service

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@Override
	public List<Customer> getAll() {
		return this.customerRepository.findAll();
	}

	
	@Override
	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}


	@Override
	public Customer update(Customer customer) throws Exception {
		Optional<Customer> optCustomer = this.customerRepository.findById(customer.getId());
		if(!optCustomer.isPresent())
		{
			throw new Exception();
		}
		else
		{	
		Customer repCustomer = optCustomer.get();
		repCustomer.setFirstName(customer.getFirstName());
		repCustomer.setLastName(customer.getLastName());
		repCustomer.setEmailId(customer.getEmailId());
		repCustomer.setCity(customer.getCity());
		repCustomer.setAddress(customer.getAddress());
		repCustomer.setMobileNo(customer.getMobileNo());

		return this.customerRepository.save(repCustomer);
		}
	}

	
	@Override
	public Customer delete(long id) {
		this.customerRepository.deleteById(id);
		return customerRepository.getById(id);
	}

	
	@Override
	public Optional<Customer> getCustomerById(long id) {
		return this.customerRepository.findById(id);
	}

    @Override
    public long checkEmailId(String emailId)
    {
    	
    	Customer cust = this.customerRepository.findByEmailId(emailId);
    	if(cust!=null)
    	{
    		return cust.getId();
    	}
    	return 0;
    }

}