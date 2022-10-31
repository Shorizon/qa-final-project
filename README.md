# VHS Rental User Storage Application

The objective of this assignment is to create webservices which store information about users and the VHS they are renting from the VHS rental shop.
Users are able to login and add VHS they'd like to rent the list of their currently rented VHS. They are also able to change information about their account.



Problem Statement

Create webservices using SpringBoot with the following mentioned components:

1.mySQL database, to store data about the users and VHS.

2.User and VHS entinties, separate from each other.

3.components to edit and display data from the database.


Tech Stack

This application has been built using the following tech stack:

1.Java SE 17
2.Maven 3.8.6
3.SpringBoot 2.7.5
4.MySQL 8.0.31 
5.Swagger-ui 3.0.0

Steps

1.Add Users through the corresponding end point by compiling a user.json and sending it with post request "localhost:8083/api/v1/users/"

2.Request a GET request using the corresponding end point to check all users in the database "localhost:8083/api/v1/users/"

3.Fetch data from a single user by using a GET request "localhost:8083/api/v1/users/{id}"

4.Add VHS to the database by sending a VHS.JSON and by running a POST request "localhost:8083/api/v1/vhs/"

5.to add VHS to the user directly run a POST request "localhost:8083/api/v1/users/{id}/vhs" with the VHS.JSON

6.To return a list of all the rented VHS run a GET request"localhost:8083/api/v1/vhs/rented_list"

7.To remove a VHS from a user run a DELETE request with "localhost:8083/api/v1/users/{id}/vhs"]

8.If any other operation is required follow the endpoint description in "list of endpoint" images.


End Points


signIn:
images/signin

findbyID:
images/findbyid

getAllUsers:
images/getallusers

list of endpoint:
images/endpoint1
images/endpoint2









