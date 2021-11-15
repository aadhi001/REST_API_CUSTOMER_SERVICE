package com.rocketlane.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rocketlane.spring.jpa.postgresql.model.Customer;
import com.rocketlane.spring.jpa.postgresql.service.CustomerService;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerController {

	
	@Autowired
	CustomerService customerservice;


	//GET method to retrieve the details of all customers
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		try {
			List<Customer> customers = this.customerservice.getAll();
			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET method to retrieve the details of a specific customer using customer id
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		Optional<Customer> customerData = this.customerservice.getCustomerById(id);
		if (customerData.isPresent()) {
			return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//POST method to create a new customer record in the database
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {
			Customer cust = new Customer(customer.getId(),customer.getFirstName(), customer.getLastName(), customer.getEmailId(), customer.getCity(), customer.getAddress(), customer.getMobileNo());
			if(this.customerservice.checkEmailId(cust.getEmailId())!=0)
			 {
				cust.setId(this.customerservice.checkEmailId(cust.getEmailId()));
			    this.customerservice.update(cust);
			    return new ResponseEntity<>(cust, HttpStatus.CREATED);
			  }
			else
			{
			Customer _customer = this.customerservice.add(cust);
			return new ResponseEntity<>(_customer, HttpStatus.CREATED);
			}
		} 
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//PUT method to update customer record given the id
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		try {
		Customer cust = new Customer(id,customer.getFirstName(), customer.getLastName(), customer.getEmailId(), customer.getCity(), customer.getAddress(), customer.getMobileNo());
		Customer customerData = this.customerservice.update(cust);
        return new ResponseEntity<>(customerData,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); 
		}
	}
	
	//DELETE method to delete a customer record given the id
	@DeleteMapping("/customers/{id}")
	
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {	
            Customer cust = this.customerservice.delete(id);
            if(cust!=null)
            {           
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else 
            {
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
	}


}


