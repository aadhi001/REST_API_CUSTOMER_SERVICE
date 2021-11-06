package com.rocketlane.spring.jpa.postgresql.controller;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    
    @MockBean(name="customerRepository")
    private CustomerRepository customerRepository;
    

    Customer Customer_1 = new Customer(1,"Arul", "Ram", "arul@gmail.com", "Trichy", "456 Gandhi Road", "9840403923");
    Customer Customer_2 = new Customer(2,"Amith", "Krishna", "amith@gmail.com", "Namakkal", "456 Gandhi Road", "9840403923");
    Customer Customer_3 = new Customer(3,"Janath", "Kishore", "janath@gmail.com", "Theni", "427 Cross street", "9840403923");
    
    @Test
    public void getCustomerById_success() throws Exception {
        Mockito.when(customerRepository.findById(Customer_1.getId())).thenReturn(java.util.Optional.of(Customer_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Arul")));
    }
}