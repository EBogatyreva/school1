package ru.hogwarts.school1.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.exception.NotFoundException;
import ru.hogwarts.school1.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Long getId = 1L;
    private Map<Long, Faculty> facultyMap = new HashMap<>();

/*    private Long getId() {
        Long result = index;
        index = index + 1;
        return result;
    }*/

/*    public FacultyService() {
        this.facultyMap = new HashMap<>();
        facultyMap.put(getId(), new Faculty("Гриффиндор", "красно-желтый"));
        facultyMap.put(getId(), new Faculty("Слизерин", "зеленый"));
        facultyMap.put(getId(), new Faculty("Когтевран", "красно-желтый"));
        facultyMap.put(getId(), new Faculty("Пуффендуй", "желтый"));
    }*/

    public Faculty createFacility(Faculty faculty) {
        facultyMap.put(getId, faculty);
        getId++;
        return faculty;
    }

    public Faculty getFacultyById(Long facilityId) {
        return facultyMap.get(facilityId);
    }

    public Faculty updateFaculty(Long facilityId, Faculty faculty) {
        facultyMap.put(getId, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facilityId) {
        return facultyMap.remove(facilityId);
    }

    public Collection<Faculty> findFaculty(String color) throws NotFoundException {
        return facultyMap.values().stream()
                .filter(students -> students.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
