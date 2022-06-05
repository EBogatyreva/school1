package ru.hogwarts.school1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
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

    @PostMapping("create")
    public ResponseEntity createfaculty(@RequestBody Faculty faculty) {
        Faculty createFacility = facultyService.createFacility(faculty);
        return ResponseEntity.ok(createFacility);
    }

    @GetMapping("{id}")
    public ResponseEntity getfaculty(@PathVariable Long id) {
        Faculty getFaculty = facultyService.getFacultyById(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();//уточнить момент!!
        }
        return ResponseEntity.ok(getFaculty);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updatefaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(id, faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deletefaculty(@PathVariable Long id) {
        Faculty deleteFaculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(deleteFaculty);
    }
    @GetMapping("/find")
    public Collection<Faculty> find(@RequestParam(required = true) String color) throws NotFoundException {
        if (color != null) {
            return facultyService.findFaculty(color);
        } else {
            throw new BadRequest();
        }
    }
}
