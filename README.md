# project aims to build following features
1. Books Inventory management
2. Employee management
3. Student management
4. Issue and return of books with penalty for late submission.

#Run maven project by following commands

mvn clean install

mvn run -Dspring.profiles.active=staging -Denv=staging


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








