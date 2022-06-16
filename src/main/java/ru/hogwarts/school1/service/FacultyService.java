package ru.hogwarts.school1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFacility(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long facility) {
        return facultyRepository.findById(facility).get();
    }

    public Faculty updateFaculty(Faculty faculty) {//сделать обновление по ID
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(Long facilityId) {
        facultyRepository.deleteById(facilityId);
    }

    public List<Faculty> findByColorLike(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findFacultyByNameOrColor(String something) {
        return facultyRepository.findFacultyByNameOrColor(something, something);
    }

    public Collection<Student> findStudents(Long faculty) {
        Faculty faculty1 = facultyRepository.findById(faculty).get();
        return faculty1.getStudents();
    }
}
