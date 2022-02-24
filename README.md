## Getir Test Case -- Görkem Hondoroğlu--


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `GetirAppApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:


# Getting Started 
ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations

#### Tech Stack
-Java 11

-SpringBoot

-MongoDB

-Maven

-Docker

#### Design

I used packages by feature so all the implementations of a feature are packaged together.

For Model design we have the basic needs.

-Book
-Customer
-Order
-Statistic 
These 4 are the core parts of the modeling phase but;
In order to get the correct amount of books that are ordered,
i have created another entity called OrderBooks which consist of a List of books and the count. 

For the statistic we had to do it monthly so we have another entity called StatisticDetails where you can get te actual
asked attributes such as totalamount, cost , totalorder. This entity is an attribute of 
Statistic so you can filter it montly and for every customer. 


#### How to Use
MongoDB database named "GetirBook" running on port 27017

For testing this solution clone the repository and then,
Use Maven build lifecycles clean install.
jwt-key, mongo-db info and servlet info can be found on application.yml
There is no other configuration. Simply run as spring boot app.

 
#### Testing
-For each end-point test could be done both on swagger and postman. For local test application.properties config should be used.

#### Authentication & Swagger

**POST**: *localhost:8080/authenticate*

JSON:

{
"username": "getir",
"password": "password"
}

Response: token...

Swagger link : http://localhost:8080/getir-app/swagger-ui.html

To be able to use swagger without jwt authentication, you need to configure AUTHENTICATION_WHITELIST by 
adding       "/api/**" to the list. 

After the all endpoint will be callable via swagger.





