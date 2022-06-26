CREATE TABLE Peoples
(
    ID             REAL,
    Name           TEXT PRIMARY KEY,
    Age            int,
    Driver_license boolean DEFAULT false,
    Car_Id         TEXT REFERENCES Cars (Car_Id)

);

CREATE TABLE Cars
(
    Car_Id TEXT PRIMARY KEY,
    Model  varchar(255),
    Brand  varchar(255),
    Cost   NUMERIC
);

SELECT Peoples.ID,
       Peoples.Name,
       Peoples.Age,
       Cars.Car_Id,
       Cars.Model,
       Cars.Brand,
       Cars.Cost
FROM Peoples
         INNER JOIN Cars ON Peoples.Car_Id = Cars.Car_Id


