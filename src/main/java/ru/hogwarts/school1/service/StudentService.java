package ru.hogwarts.school1.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.exception.NotFoundException;
import ru.hogwarts.school1.model.Student;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Long getId = 1L;
    private Map<Long, Student> studentMap;
/*
    Faculty faculty1 = new Faculty("Гриффиндор", "красно-желтый");
    Faculty faculty2 = new Faculty("Слизерин", "зеленый");
    Faculty faculty3 = new Faculty("Когтевран", "сине-оранжевый");
    Faculty faculty4 = new Faculty("Пуффендуй", "желтый");*/

/*    private Long getId() {
        Long result = index;
        index = index + 1;
        return result;
    }*/

/*    public StudentService() {
        this.studentMap = new HashMap<>();
        studentMap.put(getId(), new Student("A", 17, faculty1));
        studentMap.put(getId(), new Student("B", 16, faculty2));
        studentMap.put(getId(), new Student("C", 16, faculty3));
        studentMap.put(getId(), new Student("D", 17, faculty4));
    }*/

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
