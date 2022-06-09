package ru.hogwarts.school1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.StudentService;
import ru.hogwarts.school1.exception.BadRequest;
import ru.hogwarts.school1.exception.NotFoundException;

import java.util.Collection;

@RestController
@RequestMapping("student")
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

    @GetMapping("{id}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student getStudent = studentService.getStudentById(id);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();//уточнить момент!!
        }
        return ResponseEntity.ok(getStudent);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("delete/{id}")
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

}
