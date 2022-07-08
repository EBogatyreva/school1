package ru.hogwarts.school1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired//
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.info("Was invoked method getStudentById");
        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent(Student student) {//сделать обновление по ID
        logger.info("Was invoked method updateStudent");
        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method deleteStudent");
        studentRepository.deleteById(studentId);
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method findByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFaculty(Long studentId) {
        logger.info("Was invoked method findFaculty");
        Student student1 = studentRepository.findById(studentId).get();
        return student1.getFaculty();
    }

    public List getAll() {
        logger.info("Was invoked method getAll");
        return studentRepository.findAll();
    }

    public Integer countStudentById() {
        logger.info("Was invoked method countStudentById");
        return studentRepository.countStudentById();
    }

    public Double avarageAgeOfStudents() {
        logger.info("Was invoked method avarageAgeOfStudents");
        return studentRepository.avarageAgeOfStudents();
    }

    public List<Student> lastStudents() {
        logger.info("Was invoked method lastStudents");
        return studentRepository.lastStudents();
    }

    //_______ДЗ 4.5.
    public List<Student> findAll() {
        return studentRepository.findAll().stream().sorted()
                .filter(s -> s.getName().contains("A"))
                .collect(Collectors.toList());
    }

    public Double averageAge() {
        return studentRepository.findAll().stream()
                .collect(Collectors.averagingInt(Student::getAge));
    }

    public Integer parallel() {
         int sum = Stream.iterate(1, a -> a +1).limit(1_000_000).parallel().reduce(0, (a, b) -> a + b );
        return sum;
    }


}
