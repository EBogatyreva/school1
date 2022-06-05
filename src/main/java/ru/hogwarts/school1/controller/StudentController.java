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

    @PostMapping("create")
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("{userId}")
    public ResponseEntity getStudent(@PathVariable Long id) {
        Student getStudent = studentService.getStudentById(id);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();//уточнить момент!!
        }
        return ResponseEntity.ok(getStudent);
    }

    @PutMapping("upDate")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student.getId(), student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteStudent(@RequestBody Long id) {
        Student deleteStudent = studentService.deleteStudent(id);
        return ResponseEntity.ok(deleteStudent);
    }

    @GetMapping("/find")
    public Collection<Student> find(@RequestParam(required = true) int age) throws NotFoundException {
        if (age > 0) {
            return studentService.findStudent(age);
        } else {
            throw new BadRequest();
        }
    }

}
