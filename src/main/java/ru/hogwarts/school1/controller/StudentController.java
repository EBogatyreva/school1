package ru.hogwarts.school1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.StudentService;
import ru.hogwarts.school1.exception.BadRequest;
import ru.hogwarts.school1.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student getStudent = studentService.getStudentById(id);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();//уточнить момент!!
        }
        return ResponseEntity.ok(getStudent);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public Collection<Student> find(@RequestParam(required = true) int age) throws NotFoundException {
        if (age > 0) {
            return studentService.findByAge(age);
        } else {
            throw new BadRequest();
        }
    }

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam(required = true) int min, @RequestParam(required = true) int max) throws NotFoundException {
        if (max > 0) {
            return studentService.findByAgeBetween(min, max);
        } else {
            throw new BadRequest();
        }
    }

    @GetMapping("/findFaculty")
    public Faculty findFaculty(@RequestParam(required = true) Long studentId) throws NotFoundException {
        if (studentId > 0) {
            return studentService.findFaculty(studentId);
        } else {
            throw new BadRequest();
        }
    }

    @GetMapping("/getAll")
    public List getAll() {
        return studentService.getAll();
    }

    @GetMapping
    public String testAPI() {
        return "Web API is working";
    }

    @GetMapping("/countStudentById")
    public Integer countStudentByIds() {
        return studentService.countStudentById();
    }

/*    @GetMapping("avarageAgeOfStudents")
    public Double avarageAgeOfStudents() {
        return studentService.avarageAgeOfStudents();
    }*/

    @GetMapping("/lastStudents")
    public List<Student> lastStudents() {
        return studentService.lastStudents();
    }

    //__ДЗ 4.5.
    @GetMapping("/findAll")
    public List<String> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/avarageAge")
    public Double avarageAge() {
        return studentService.averageAge();
    }
    @GetMapping("/parallel")
    public Long parallel() {
        return studentService.parallel();
    }

    @GetMapping("/name")
    public void name() {
        studentService.name();
    }
    @GetMapping("/sinName")
    public void sinName() {
        studentService.sinName();
    }


}
