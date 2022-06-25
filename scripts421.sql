ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age < 16),
 name TEXT UNIQUE NOT NULL;

ALTER
COLUMN student.age
         SET DEFAULT '20';
ALTER TABLE faculty
    ADD CONSTRAINT faculty_name_unique UNIQUE (name, color);



