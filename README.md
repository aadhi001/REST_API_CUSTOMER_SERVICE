# 🚀ROCKETLANE CODING ASSIGNMENT🚀
### 📒***SERVICE TO MANAGE CUSTOMER INFORMATION***

📌**Aim** :
To create a service to manage the Customer details. Using this service we should be able to store and retrieve the customer informations.

>FRAMEWORK USED : SPRINGBOOT </br>
>DATABASE: POSTGRESQL

### ✨✨**PACKAGES AND LAYERS IN THIS PROJECT**✨✨
#### 📍 1.	DOMAIN LAYER – Creating Customer Model
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.MODEL`
The data model (Customer) is defined with the fields: ID, FIRSTNAME, LASTNAME, EMAILID, MOBILENO, CITY and ADDRESS.

#### 📍2.	PERSISTENCE LAYER – Creating Customer Repository
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.REPOSITORY`
In the repository package, CustomerRepository interface extends JpaRepository .
It provides method for easy retrieval from the database without the need for manual implementation.

#### 📍 3.	BUSINESS LAYER- Create Customer Controller
Package: `COM.ROCKETLANE.SPRING.JPA.POSTGRESQL.CONTROLLER`</br>
Customer controller is the controller which exposes REST endpoints for the CRUD Operation.</br>
**🚀CREATE- HTTP POST METHOD**</br>
One POST method to create a new customer record in the database.</br>
**🚀READ- HTTP GET METHOD**</br>
Two GET methods to retrieve customer records from the database. One gets all the customer details and the other gets a single record given a customer ID.</br>
**🚀UPDATE- HTTP PUT METHOD**</br>
One PUT method to update a customer record in the database given the customer id of the record to be updated is given.</br>
**DELETE- HTTP DELETE METHOD**</br>
One DELETE method to delete a customer record in the database given the customer id of the record to be deleted.</br>


### ✔Implemented APIs':
| Method | API|
| ------ | ------ |
| CREATE |HTTP POST http://localhost:8080/customers|
| READ | HTTP GET http://localhost:8080/customers/2 |
| UPDATE | HTTP PUT http://localhost:8080/customers/2 |
| DELETE | HTTP DELETE http://localhost:8080/customers/2 |

### HTTP Status Codes used in this project

|Status Code|Details|
|------|------|
|200|Success - OK|
|201|Success - Created|
|204|Sucess - No Content|
|400|ClientError - Bad Request|
|404|ClientError - Not Found|
|500|Server Error - Internal Server Error|

### Unit Testing:

|Testcase| Purpose |Result|
|------|------|------|
|1. getAllCustomers_success()|To check if all customer record is retrieved and status code is OK|✔|
|2. getAllCustomers_EmptyDatabase()|Checking if NoContent status is sent if the database is empty.|✔|
|3. getCustomerById_success()|Testing if the getCustomerById works correctly and returns success status code if given a valid ID.|✔|
|4. getCustomerbyId_InvalidId()|Testing if the getCustomerById method returns not found status given an incorrect id.|✔|
|5. postCustomer_success()|Checking if a new customer record is created successfully given the valid info |✔|
|6. postCustomer_failure()|Checking if creating a incorrect record results in bad request.|✔|
|7. putCustomer_success()|Checking if put method is working perfectly if given the correct id to be updated.|✔|
|8. putCustomer_recordNotFound() |Checking if not found status is sent if incorrect id is given.|✔|
|9. deleteCustomerbyId_success|Checking if deletion works fine if given a valid ID.|✔|
|10. deleteCustomerbyId_notFound()|Checking if bad request status is sent if given a invalid id|✔|
