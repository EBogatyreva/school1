package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school1.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    Integer countStudentById();

    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    Double avarageAgeOfStudents();

    @Query(value = "SELECT * FROM student order by id desc limit 5", nativeQuery = true)
    List<Student> lastStudents();
}
