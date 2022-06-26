SELECT s.name,
       s.age,
       s.faculty_id

FROM student s
         INNER JOIN faculty f ON s.faculty_id = f.id;


SELECT s.name,
       s.age,
       s.avatar_id

FROM student s
         INNER JOIN avatar a ON s.avatar_id = a.id
WHERE avatar_id is not null;
