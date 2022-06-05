package ru.hogwarts.school1.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.exception.NotFoundException;
import ru.hogwarts.school1.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Long getId = 1L;
    private Map<Long, Student> studentMap = new HashMap<>();

    public Student createStudent(Student student) {
        studentMap.put(getId,student);
        getId++;
        return student;
    }
    public Student getStudentById(Long studentId) {
        return studentMap.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        studentMap.put(studentId, student);
        return student;
    }
    public Student deleteStudent(Long studentId) {
        return studentMap.remove(studentId);
    }

    public Collection<Student> findStudent(int age) throws NotFoundException {
        return studentMap.values().stream()
                .filter(students -> students.getAge() == age)
                .collect(Collectors.toList());
    }

}
