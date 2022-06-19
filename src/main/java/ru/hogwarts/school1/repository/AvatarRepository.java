package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school1.model.Avatar;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;

import java.util.List;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
    Avatar findAvatarById (Long id);
}
