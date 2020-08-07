# project
1. Books Inventory management
2. Employee management
3. Student management
4. Issue and return of books with penalty for late submission.


CREATE TABLE book(id INT PRIMARY KEY NOT NULL, title CHAR(100) NOT NULL, subject CHAR(100) NOT NULL, author CHAR(100) NOT NULL, is_issued BOOLEAN DEFAULT FALSE, is_active BOOLEAN DEFAULT TRUE);

CREATE TABLE employee(id INT PRIMARY KEY NOT NULL, employee_type VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, address TEXT NOT NULL, phone_number bigint NOT NULL, role VARCHAR(100) NOT NULL, is_active BOOLEAN DEFAULT TRUE);

CREATE TABLE student(id INT PRIMARY KEY NOT NULL, student_type VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL, address TEXT NOT NULL, phone_number bigint NOT NULL, is_active BOOLEAN DEFAULT TRUE);

CREATE TABLE issued_book(id INT PRIMARY KEY NOT NULL, book_id INT NOT NULL, student_id INT NOT NULL, issuer_id INT NOT NULL, receiver_id INT, issued_date timestamp NOT NULL, due_date timestamp NOT NULL, date_returned timestamp, total_rent real NOT NULL, total_fine real, is_returned BOOLEAN DEFAULT FALSE, fine_paid real, comment VARCHAR(255), is_active BOOLEAN DEFAULT TRUE, 
CONSTRAINT fk_issued_book FOREIGN KEY(book_id) REFERENCES book(id), FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(issuer_id) REFERENCES employee(id), FOREIGN KEY(receiver_id) REFERENCES employee(id));
