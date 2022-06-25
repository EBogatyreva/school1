CREATE TABLE Readers
(
    ID          REAL,
    Name_reader TEXT PRIMARY KEY,
    Address     varchar(255),
    Book_Id     TEXT REFERENCES Books (Book_Id)

);

CREATE TABLE Books
(
    Book_Id             TEXT PRIMARY KEY,
    Name_Book           varchar(255),
    Author              varchar(255),
    Year_of_publication int
);

SELECT Readers.ID,
       Readers.Name_reader,
       Readers.Address,
       Books.Book_Id,
       Books.Name_Book,
       Books.Author,
       Books.Year_of_publication
FROM Readers
         INNER JOIN Books ON Readers.Book_Id = Books.Book_Id


SELECT student.name,
       student.age,
       student.faculty_id
FROM faculty
         INNER JOIN faculty_id ON student.faculty_id = faculty.id
