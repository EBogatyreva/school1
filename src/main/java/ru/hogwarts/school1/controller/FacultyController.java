package ru.hogwarts.school1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.FacultyService;
import ru.hogwarts.school1.exception.BadRequest;
import ru.hogwarts.school1.exception.NotFoundException;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createfaculty(@RequestBody Faculty faculty) {
        Faculty createFacility = facultyService.createFacility(faculty);
        return ResponseEntity.ok(createFacility);
    }

    @GetMapping("{id}")
    public ResponseEntity getfaculty(@PathVariable Long id) {
        Faculty getFaculty = facultyService.findFacultyById(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();//уточнить момент!!
        }
        return ResponseEntity.ok(getFaculty);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updatefaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deletefaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public Collection<Faculty> findByColorLike(@RequestParam(required = true) String color) throws NotFoundException {
        if (color != null) {
            return facultyService.findByColorLike(color);
        } else {
            throw new BadRequest();
        }
    }

    @GetMapping("/findFacultyByNameOrColor")
    public Collection<Faculty> findFacultyByNameOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        if ((name != null) || (color != null)) {
            return facultyService.findFacultyByNameOrColor(name, color);
        } else {
            throw new BadRequest();
        }
    }

    @GetMapping("/findStudentsByFaculty")
    public Collection<Student> find(@RequestParam(required = true) Long faculty) throws NotFoundException {
        if (faculty != null) {
            return facultyService.findStudents(faculty);
        } else {
            throw new BadRequest();
        }
    }



}
