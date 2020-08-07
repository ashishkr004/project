# project aims to build following features
1. Books Inventory management
2. Employee management
3. Student management
4. Issue and return of books with penalty for late submission.

#### Implemented following things in the project
# Built the system to manage books, employees and students data with ability to add, search and update new books, employees and students.
# We can issue, track and return books form/to library
# Roles are defined for employee so that employees can only do their respective task
# CRUD APIs for book, employee, student and issued_book table
# If book is already issued to someone it can't be re-issued until it is not return to library
# Employee with role 'Admin' has rights to add new employees and students 
# Employee with roles 'librarian' and 'issuer' can issue books to students
# Employee with roles 'librarian' and 'receiver' can allow return of books
# Search Api provides filter on various attribute
# Exceptions are handled
# 2-3 rounds of dev testing are done

#Technologies and language used:
Java 11, Spring-boot, PostgreSql, Swagger

#Run maven project by following commands

mvn clean install

mvn spring-boot:run -Dspring.profiles.active=staging -Denv=staging


#Run following commands after starting psql server

#Run psql server
brew services start postgresql

#create database
CREATE DATABASE library;

#create table book
CREATE TABLE book(id SERIAL PRIMARY KEY, title VARCHAR(255) NOT NULL, subject VARCHAR(100) NOT NULL, author VARCHAR(100) NOT NULL, is_issued BOOLEAN NOT NULL, is_active BOOLEAN NOT NULL);

#create table employee
CREATE TABLE employee(id INT PRIMARY KEY NOT NULL, employee_type VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, address TEXT NOT NULL, phone_number bigint NOT NULL, role VARCHAR(100) NOT NULL, is_active BOOLEAN DEFAULT TRUE);

#insert admin in employee table
INSERT INTO employee(employee_type, password, name, address, phone_number, role, is_active) VALUES ('admin', '12345', 'ram', 'blr', 9123456789, 'admin', true);

#create table student
CREATE TABLE student(id SERIAL PRIMARY KEY, student_type VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, address TEXT NOT NULL, phone_number bigint NOT NULL, is_active BOOLEAN NOT NULL);

#create table issued_book
CREATE TABLE issued_book(id INT PRIMARY KEY NOT NULL, book_id INT NOT NULL, student_id INT NOT NULL, issuer_id INT NOT NULL, receiver_id INT, issued_date timestamp NOT NULL, due_date timestamp NOT NULL, date_returned timestamp, total_rent real NOT NULL, total_fine real, is_returned BOOLEAN DEFAULT FALSE, fine_paid real, comment VARCHAR(255), is_active BOOLEAN DEFAULT TRUE, 
CONSTRAINT fk_issued_book FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(issuer_id) REFERENCES employee(id), FOREIGN KEY(receiver_id) REFERENCES employee(id));


#These are some important curls:
1. To add new book
curl --location --request POST 'http://localhost:8080/project/v1.0/books' \
--header 'Content-Type: application/json' \
--data-raw '{
	"booksList": [{
		"title": "The fault in our Stars",
		"subject": "Novel",
		"author": "John Green"
	}],
	"employeeId": 5
}'

2. To add new employee
curl --location --request POST 'http://localhost:8080/project/v1.0/employees' \
--header 'Content-Type: application/json' \
--data-raw '{
	"employeesList": [{
		"employeeType": "permanent",
		"password": "2345",
		"name": "aka",
	  	"address": "motihari",
		"phoneNumber": "9912345679",
		"role": "receiver",
	  	"isActive": true
	}],
	"employeeId": 1
}'

3. To add new student
curl --location --request POST 'http://localhost:8080/project/v1.0/students' \
--header 'Content-Type: application/json' \
--data-raw '{
	"studentsList": [{
		"studentType": "permanent",
		"password": "2345",
		"name": "aka",
	  	"address": "motihari",
		"phoneNumber": "9912345679",
		"role": "receiver",
	  	"isActive": true
	}],
	"employeeId": 1
}'

4. To search books in library
curl --location --request GET 'http://localhost:8080/project/v1.0/books'

5. To search employees in db
curl --location --request GET 'http://localhost:8080/project/v1.0/employees'

6. To search students in db
curl --location --request GET 'http://localhost:8080/project/v1.0/students'

7. update books in library 
curl --location --request PATCH 'http://localhost:8080/project/v1.0/books' \
--header 'Content-Type: application/json' \
--data-raw '{
	"booksList": [{
		"id": 2,
		"title": "The Fault In Our Stars",
		"subject": "Novel",
		"author": "John Green",
		"isIssued": false
	},
	{
		"id": 1,
		"title": "My Experiments With Truth",
		"subject": "Novel",
		"author": "Mahatma Gani",
		"isIssued": false
	}],
	"employeeId": 3
}'

8. update employees curl
curl --location --request PATCH 'http://localhost:8080/project/v1.0/employees' \
--header 'Content-Type: application/json' \
--data-raw '{
	"employeesList": [        {
            "id": 7,
            "employeeType": "library",
            "password": "2345",
            "name": "aka",
            "address": "motihari",
            "phoneNumber": 9912345678,
            "role": "receiver",
            "isActive": true
        },
        {
            "id": 8,
            "employeeType": "library",
            "password": "2345",
            "name": "aka",
            "address": "motihari",
            "phoneNumber": 9912345678,
            "role": "receiver",
            "isActive": true
        }],
	"employeeId": 1
}'

9. update students curl api
curl --location --request PATCH 'http://localhost:8080/project/v1.0/students' \
--header 'Content-Type: application/json' \
--data-raw '{
	"studentsList": [{
		"id": 1,
		"studentType": "College",
		"password": "2345",
		"name": "Ashok",
	  	"address": "motihari",
		"phoneNumber": "9912345679",
		"role": "receiver",
	  	"isActive": true
	},
	{
            "id": 2,
            "studentType": "school",
            "password": "2345",
            "name": "amit",
            "address": "motihari",
            "phoneNumber": 9912345679,
            "isActive": true
        }],
	"employeeId": 1
}'

10. curl to issue books
curl --location --request POST 'http://localhost:8080/project/v1.0/library' \
--header 'Content-Type: application/json' \
--data-raw '{
	"issuedBooks": [{
		"bookId": 1,
		"studentId": 1,
		"issuerId": 1,
	  	"issuedDate": 1596820720,
		"dueDate": 1596820720,
		"totalRent": 10.0,
	  	"comment": "novel"
	}],
	"employeeId": 5
}'

11. curl to return books
curl --location --request PATCH 'http://localhost:8080/project/v1.0/library' \
--header 'Content-Type: application/json' \
--data-raw '{
	"issuedBooks": [{
		"id": 6,
		"bookId": 2,
		"studentId": 2,
		"issuerId": 1,
	  	"issuedDate": 1596820720,
		"dueDate": 1596820720,
		"totalRent": 10.0,
		"totalFine": 10.0,
	  	"comment": "novel"
	}],
	"employeeId": 5
}'


### Missed
# Junit for testing

## Feature Works
# We can create multiple micro-service to manage books, employees, student and library independently. Which will communicated each other through rest api calls.
# Junit and Mockito for testing and jacacoo for test coverage
# Proper logging of the data
# logs table to track each transaction in database







