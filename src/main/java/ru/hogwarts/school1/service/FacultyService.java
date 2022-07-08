package ru.hogwarts.school1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFacility(Faculty faculty) {
        logger.info("Was invoked method for create facility");
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long facility) {
        logger.info("Was invoked method findFacultyById");
        return facultyRepository.findById(facility).get();
    }

    public Faculty updateFaculty(Faculty faculty) {//сделать обновление по ID
        logger.info("Was invoked method for update facility");
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(Long facilityId) {
        logger.info("Was invoked method for delete facility");
        facultyRepository.deleteById(facilityId);
    }

    public List<Faculty> findByColorLike(String color) {
        logger.info("Was invoked method findByColorLike");
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findFacultyByNameOrColor(String something) {
        logger.info("Was invoked method findFacultyByNameOrColor");
        return facultyRepository.findFacultyByNameOrColor(something, something);
    }

    public Collection<Student> findStudents(Long faculty) {
        logger.info("Was invoked method for find students by faculty");
        Faculty faculty1 = facultyRepository.findById(faculty).get();
        return faculty1.getStudents();
    }


    public Optional<Faculty> finTheLongestName() {
        return facultyRepository.findAll().stream()
             .reduce((s1, s2) -> {
            if (s1.getName().length() > s2.getName().length())
                return s1;
            else
                return s2;
        });
    }
}
