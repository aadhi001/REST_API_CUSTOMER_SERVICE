# ROCKETLANE CODING ASSIGNMENT
### ***SERVICE TO MANAGE CUSTOMER INFORMATION***

**Aim** :
To create a service to manage the Customer details. Using this service we should be able to store and retrieve the customer informations.

>FRAMEWORK USED : SPRINGBOOT 
>DATABASE: POSTGRESQL

### **PACKAGES AND LAYERS IN THIS PROJECT**
#### 1.	DOMAIN LAYER – Creating Customer Model
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.MODEL`
The data model (Customer) is defined with the fields: ID, FIRSTNAME, LASTNAME, EMAILID, MOBILENO, CITY and ADDRESS.

#### 2.	PERSISTENCE LAYER – Creating Customer Repository
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.REPOSITORY`
In the repository package, CustomerRepository interface extends JpaRepository .
It provides method for easy retrieval from the database without the need for manual implementation.

#### 3.	BUSINESS LAYER- Create Customer Controller
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.CONTROLLER`
Customer controller is the controller which exposes REST endpoints for the CRUD Operation.
>**CREATE- HTTP POST METHOD**
>One POST method to create a new customer record in the database.
**READ- HTTP GET METHOD**
>Two GET methods to retrieve customer records from the database. One gets all the >customer details and the other gets a single record given a customer ID.
**UPDATE- HTTP PUT METHOD**
>One PUT method to update a customer record in the database given the customer id of the >record to be updated is given.
**DELETE- HTTP DELETE METHOD**
>One DELETE method to delete a customer record in the database given the customer id of >the record to be deleted.


### Implemented APIs':
| Method | API|
| ------ | ------ |
| CREATE |HTTP POST http://localhost:8080/customers|
| READ | HTTP GET http://localhost:8080/customers/2 |
| UPDATE | HTTP PUT http://localhost:8080/customers/2 |
| DELETE | HTTP DELETE http://localhost:8080/customers/2 |
