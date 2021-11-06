package com.rocketlane.spring.jpa.postgresql.model;
import javax.persistence.*;

import org.springframework.lang.NonNull;

/*
 We need to store the below customer details in our persistence storage. We should be
able to update and retrieve the below informations:
firstName.
lastName.
emailId.
mobileNo.
city.
address.
 */

@Entity //To specify that class is mapped to a table in the database
@Table(name = "customers") //To make sure that the class is pointing to the right table
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NonNull
	@Column(name = "firstName")
	private String firstName;
    
	@NonNull
	@Column(name = "lastName")
	private String lastName;
	
	@NonNull
	@Column(name = "emailId")
	private String emailId;

	@NonNull
	@Column(name = "city")
	private String city;
	
	@NonNull
	@Column(name = "address")
	private String address;
	
	@NonNull
	@Column(name = "mobileNo")
	private String mobileNo;
	
	public Customer() {

	}

	//Constructor
	public Customer(long id,String firstName, String lastName, String emailId, String city, String address, String mobileNo) 
	{
	   this.id=id;
       this.firstName=firstName;
       this.lastName=lastName;
       this.emailId=emailId;
       this.city=city;
       this.address=address;
       this.mobileNo=mobileNo;
	}

	//getter and setter methods
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ",emailId="+ emailId +",city="+city+",address="+ address +",mobileNo="+mobileNo+"]";
	}
}
