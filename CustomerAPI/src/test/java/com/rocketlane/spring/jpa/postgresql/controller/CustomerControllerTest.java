package com.rocketlane.spring.jpa.postgresql.controller;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocketlane.spring.jpa.postgresql.model.Customer;
import com.rocketlane.spring.jpa.postgresql.repository.CustomerRepository;




@WebMvcTest(CustomerController.class)

public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
   //Creating a mock repository using MockBean.
   @MockBean
   CustomerRepository customerRepository;
    
    //Created 3 mock records for testing purpose
    Customer Customer_1 = new Customer(1,"Arul", "Ram", "arul@gmail.com", "Trichy", "456 Gandhi Road", "9840403923");
    Customer Customer_2 = new Customer(2,"Amith", "Krishna", "amith@gmail.com", "Namakkal", "456 Gandhi Road", "9840403923");
    Customer Customer_3 = new Customer(3,"Janath", "Kishore", "janath@gmail.com", "Theni", "427 Cross street", "9840403923");
    
    //GET Method Testing
    
    //a.Testing if the getCustomerById works correctly and returns success status code if given a valid ID.
    @Test
    public void getCustomerById_success() throws Exception {
        Mockito.when(customerRepository.findById(Customer_1.getId())).thenReturn(java.util.Optional.of(Customer_1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Arul")));
        
    }
    
    //b.To check if all customer record is retrieved and status code is OK 
    @Test
    public void getAllCustomers_success() throws Exception {
        List<Customer> records = new ArrayList<>(Arrays.asList(Customer_1, Customer_2, Customer_3));
        
        Mockito.when(customerRepository.findAll()).thenReturn(records);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].firstName", is("Janath")));
    }
    
    //c.Checking if NoContent status is sent if the database is empty.
    @Test
    public void getAllCustomers_EmptyDatabase() throws Exception {
        List<Customer> records = new ArrayList<>(Arrays.asList());
        
        Mockito.when(customerRepository.findAll()).thenReturn(records);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    //d.Testing if the getCustomerById method returns not found status given an incorrect id.
    @Test
    public void getCustomerById_InvalidID() throws Exception {
    	Optional<Customer> customerData = customerRepository.findById(5l);
        Mockito.when(customerRepository.findById(5l)).thenReturn(customerData);
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/customers/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
                 
    }
    
    //Testing POST method
    
    //a. Checking if a new customer record is created successfully given the correct request
    @Test
    public void postCustomer_success() throws Exception {
        Customer record = new Customer(7,"Rajan","Shanmugham","raj@gmail.com","neyveli","Rajaji Nagar","9876598765");

        Mockito.when(customerRepository.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("http://localhost:8080/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
                
        }
    
    //b. Checking if creating a incorrect record results in bad request.
    @Test
    public void postCustomer_failure() throws Exception {
        Customer record = new Customer(7,"Rajan","Shanmugham","raj@gmail.com","neyveli","Rajaji Nagar","9876598765");

        Mockito.when(customerRepository.save(record)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("http://localhost:8080/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
            

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
                
        }
    
    
    //Testing the PUT method.
    
    //a. Checking if put method is working perfectly if given the correct id to be updated.
    @Test
    public void putCustomer_success() throws Exception {
        Customer updatedRecord = new Customer(3,"Rajan","Shanmugh","raj@gmail.com","neyveli","Rajaji Nagar","9876598765");

        Mockito.when(customerRepository.findById(Customer_3.getId())).thenReturn(Optional.of(Customer_1));
        Mockito.when(customerRepository.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("http://localhost:8080/api/customers/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    
    //b. Checking if not found status is sent if incorrect id is given.
    @Test
    public void putCustomer_recordNotFound() throws Exception {
    	//Optional<Customer> customerData = customerRepository.findById(updatedRecord.getId());
        Customer updatedRecord = new Customer();

        Mockito.when(customerRepository.findById(updatedRecord.getId())).thenReturn(Optional.of(updatedRecord));
        Mockito.when(customerRepository.save(updatedRecord)).thenReturn(updatedRecord);
        
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("http://localhost:8080/api/customers/5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound());
    }
    
    
    //Testing the delete method
    
    //a.Checking if deletion works fine if given a valid ID.
    @Test
    public void deleteCustomerById_success() throws Exception {
        Mockito.when(customerRepository.findById(Customer_2.getId())).thenReturn(Optional.of(Customer_2));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("http://localhost:8080/api/customers/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

   //b. Checking if bad request status is sent if given a invalid id
   @Test
    public void deleteCustomerById_notFound() throws Exception {
        Mockito.when(customerRepository.findById(5l)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("http://localhost:8080/api/customers/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
    
}