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

#create table student
CREATE TABLE student(id INT PRIMARY KEY NOT NULL, student_type VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, address TEXT NOT NULL, phone_number bigint NOT NULL, is_active BOOLEAN DEFAULT TRUE);

#create table issued_book
CREATE TABLE issued_book(id INT PRIMARY KEY NOT NULL, book_id INT NOT NULL, student_id INT NOT NULL, issuer_id INT NOT NULL, receiver_id INT, issued_date timestamp NOT NULL, due_date timestamp NOT NULL, date_returned timestamp, total_rent real NOT NULL, total_fine real, is_returned BOOLEAN DEFAULT FALSE, fine_paid real, comment VARCHAR(255), is_active BOOLEAN DEFAULT TRUE, 
CONSTRAINT fk_issued_book FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(issuer_id) REFERENCES employee(id), FOREIGN KEY(receiver_id) REFERENCES employee(id));
