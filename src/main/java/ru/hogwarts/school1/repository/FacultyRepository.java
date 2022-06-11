package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);

    List<Faculty> findFacultyByNameOrColor(String name, String color);

}
