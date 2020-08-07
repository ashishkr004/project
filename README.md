# project
1. Books Inventory management
2. Employee management
3. Student management
4. Issue and return of books with penalty for late submission.


CREATE TABLE book(id INT PRIMARY KEY NOT NULL, title CHAR(100) NOT NULL, subject CHAR(100) NOT NULL, author CHAR(100) NOT NULL, is_issued BOOLEAN DEFAULT FALSE, is_active BOOLEAN DEFAULT TRUE);
