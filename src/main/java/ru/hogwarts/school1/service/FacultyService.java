package ru.hogwarts.school1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.exception.NotFoundException;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private Long getId = 1L;

    public Faculty createFacility(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facilityId) {
        return facultyRepository.findById(facilityId).get();
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

}
